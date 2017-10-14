package splatoon

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

import java.time.Instant

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_TO'])
class TournamentRegistrationController {

    SpringSecurityService springSecurityService

    @Transactional
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
    def delete(TournamentRegistration tournamentRegistration) {
        if(request.method == 'DELETE') {
            def event = tournamentRegistration.event
            flash.message = "Equipe ${tournamentRegistration.team.name} a ete retiree du tournoi"
            tournamentRegistration.delete flush: true
        }
        redirect(mapping: 'admin_tournamentEvent_show', id: tournamentRegistration.event.id)
    }
}
