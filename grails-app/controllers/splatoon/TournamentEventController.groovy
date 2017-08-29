package splatoon

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.AccessDeniedException

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TournamentEventController {

    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        respond TournamentEvent.list(params << [sort: 'date', order: 'desc']), model:[tournamentEventCount: TournamentEvent.count()]
    }

    // For admin
    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def show(TournamentEvent tournamentEvent) {
        respond tournamentEvent
    }

    def showNextEvents() {
        def nextEvents = TournamentEvent.findUpcomingEvents()
        log.debug("Return ${nextEvents.size()} event(s) to come.")
        println("Return ${nextEvents.size()} event(s) to come.")
        render(view: "showNextEvents", model: [nextEvents: nextEvents])
    }

    // For general public
    def details(TournamentEvent tournamentEvent) {
        respond tournamentEvent
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def create() {
        respond new TournamentEvent(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def save(TournamentEvent tournamentEvent) {
        verifyThatUserCanManage(tournamentEvent)
        if (tournamentEvent == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tournamentEvent.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tournamentEvent.errors, view:'create'
            return
        }

        if(!tournamentEvent.tournament.organizer.canBeManagedBy(springSecurityService.currentUser as User)) {
            throw new AccessDeniedException("N'a pas la permission de gerer le tournoi.")
        }

        tournamentEvent.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tournamentEvent.label', default: 'TournamentEvent'), tournamentEvent.id])
                redirect tournamentEvent
            }
            '*' { respond tournamentEvent, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def edit(TournamentEvent tournamentEvent) {
        verifyThatUserCanManage(tournamentEvent)
        respond tournamentEvent
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def update(TournamentEvent tournamentEvent) {
        verifyThatUserCanManage(tournamentEvent)
        if (tournamentEvent == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tournamentEvent.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tournamentEvent.errors, view:'edit'
            return
        }

        tournamentEvent.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tournamentEvent.label', default: 'TournamentEvent'), tournamentEvent.id])
                redirect tournamentEvent
            }
            '*'{ respond tournamentEvent, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def delete(TournamentEvent tournamentEvent) {
        if (tournamentEvent == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        verifyThatUserCanManage(tournamentEvent)

        tournamentEvent.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tournamentEvent.label', default: 'TournamentEvent'), tournamentEvent.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournamentEvent.label', default: 'TournamentEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private void verifyThatUserCanManage(TournamentEvent tournamentEvent) throws AccessDeniedException {
        def user = springSecurityService.currentUser as User
        if (!user.canManage(tournamentEvent.tournament.organizer)) {
            throw new AccessDeniedException("Not allowed to manage tournament organizer")
        }
    }
}
