package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.AccessDeniedException

import static org.springframework.http.HttpStatus.*
import  grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class TournamentController {

    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def index(Integer max) {
        def tournaments = Tournament.list()
            .findAll { it.organizer.canBeManagedBy(springSecurityService.currentUser as User)} // TODO: Optimize at query level
        render(view: 'index', model: [tournamentList: tournaments])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def show(Tournament tournament) {
        respond tournament
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def create() {
        respond new Tournament(params)
    }

    @Transactional
    def save(Tournament tournament) {
        verifyThatUserCanManage(tournament)
        if (tournament == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tournament.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tournament.errors, view:'create'
            return
        }

        tournament.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tournament.label', default: 'Tournament'), tournament.id])
                redirect tournament
            }
            '*' { respond tournament, [status: CREATED] }
        }
    }

    private void verifyThatUserCanManage(Tournament tournament) throws AccessDeniedException {
        def user = springSecurityService.currentUser as User
        if (!user.canManage(tournament.organizer)) {
            throw new AccessDeniedException("Not allowed to manage tournament organizer")
        }
    }

    def edit(Tournament tournament) {
        verifyThatUserCanManage(tournament)
        respond tournament
    }

    @Transactional
    def update(Tournament tournament) {
        if (tournament == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        verifyThatUserCanManage(tournament)

        if (tournament.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tournament.errors, view:'edit'
            return
        }

        tournament.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tournament.label', default: 'Tournament'), tournament.id])
                redirect tournament
            }
            '*'{ respond tournament, [status: OK] }
        }
    }

    @Transactional
    def delete(Tournament tournament) {

        if (tournament == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        verifyThatUserCanManage(tournament)

        tournament.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tournament.label', default: 'Tournament'), tournament.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournament.label', default: 'Tournament'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
