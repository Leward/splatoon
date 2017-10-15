package splatoon

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.AccessDeniedException

import java.time.Instant
import java.time.LocalDate

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TournamentRegistrationController {

    SpringSecurityService springSecurityService

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def create() {
        def tournamentRegistration = new TournamentRegistration(params)
        tournamentRegistration.registeredAt = Instant.now()
        tournamentRegistration.registeredBy = springSecurityService.currentUser
        if(request.isPost() && tournamentRegistration.validate()) {
            tournamentRegistration.save flush:true
            flash.message = "Equipe ${tournamentRegistration.team.name} enregitree sur le tournoi"
            redirect(mapping: 'admin_tournamentEvent_show', id: tournamentRegistration.event.id)
        }
        respond tournamentRegistration
    }

    @Transactional
    @Secured('IS_AUTHENTICATED_FULLY')
    def register(TournamentEvent event) {
        def tournamentRegistration = new TournamentRegistration(params)
        tournamentRegistration.registeredAt = Instant.now()
        tournamentRegistration.registeredBy = springSecurityService.currentUser
        tournamentRegistration.event = event
        if(request.isPost() && tournamentRegistration.validatePublicRegistration(springSecurityService.currentUser)) {
            println "SAVE ME"
            tournamentRegistration.save flush:true
            flash.message = "Equipe ${tournamentRegistration.team.name} enregitree sur le tournoi"
            redirect(mapping: 'tournament_event', id: tournamentRegistration.event.id)
        }
        println "Request method: ${request.method}"
        println "Errors: ${tournamentRegistration.errors}"
        render(view: 'register', model: [
                tournamentRegistration: tournamentRegistration,
                teams: springSecurityService.currentUser.teams
        ])
    }

    @Transactional
    @Secured('IS_AUTHENTICATED_FULLY')
    def unregister(TournamentRegistration tournamentRegistration) {
        if(!tournamentRegistration.team.canBeManagedBy(springSecurityService.currentUser)) {
            throw new AccessDeniedException("Vous ne pouvez pas gerer l'equipe ${tournamentRegistration.team}")
        }
        if(!tournamentRegistration.event.isPublicRegistrationOpen()) {
            throw new AccessDeniedException("Les inscriptions au tournoi sont fermees. " +
                    "Il n'est pas possible de s'en desincrire. " +
                    "Veuillez prendre contact avec un administrateur. ")
        }
        def team = tournamentRegistration.team
        def event = tournamentRegistration.event
        if(request.isPost()) {
            tournamentRegistration.delete(flush: true)
            flash.message = "L'equipe ${team} a ete desinscrite du tournoi. "
        } else {
            fflash.message = "L'equipe ${team} n'a pas ete desinscrite du tournoi. "
        }
        redirect(mapping: 'tournament_event', id: event.id)
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def delete(TournamentRegistration tournamentRegistration) {
        if(request.method == 'DELETE') {
            def event = tournamentRegistration.event
            flash.message = "Equipe ${tournamentRegistration.team.name} a ete retiree du tournoi"
            tournamentRegistration.delete flush: true
        }
        redirect(mapping: 'admin_tournamentEvent_show', id: tournamentRegistration.event.id)
    }
}
