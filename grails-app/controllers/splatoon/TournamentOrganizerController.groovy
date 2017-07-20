package splatoon

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class TournamentOrganizerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TournamentOrganizer.list(params), model:[tournamentOrganizerCount: TournamentOrganizer.count()]
    }

    def show(TournamentOrganizer tournamentOrganizer) {
        respond tournamentOrganizer
    }

    def create() {
        respond new TournamentOrganizer(params)
    }

    @Transactional
    def save(TournamentOrganizer tournamentOrganizer) {
        if (tournamentOrganizer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tournamentOrganizer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tournamentOrganizer.errors, view:'create'
            return
        }

        tournamentOrganizer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer'), tournamentOrganizer.id])
                redirect tournamentOrganizer
            }
            '*' { respond tournamentOrganizer, [status: CREATED] }
        }
    }

    def edit(TournamentOrganizer tournamentOrganizer) {
        respond tournamentOrganizer
    }

    @Transactional
    def update(TournamentOrganizer tournamentOrganizer) {
        if (tournamentOrganizer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tournamentOrganizer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tournamentOrganizer.errors, view:'edit'
            return
        }

        tournamentOrganizer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer'), tournamentOrganizer.id])
                redirect tournamentOrganizer
            }
            '*'{ respond tournamentOrganizer, [status: OK] }
        }
    }

    @Transactional
    def delete(TournamentOrganizer tournamentOrganizer) {

        if (tournamentOrganizer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tournamentOrganizer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer'), tournamentOrganizer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
