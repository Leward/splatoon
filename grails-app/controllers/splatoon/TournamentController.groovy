package splatoon

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TournamentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tournament.list(params), model:[tournamentCount: Tournament.count()]
    }

    def show(Tournament tournament) {
        respond tournament
    }

    def create() {
        respond new Tournament(params)
    }

    @Transactional
    def save(Tournament tournament) {
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

    def edit(Tournament tournament) {
        respond tournament
    }

    @Transactional
    def update(Tournament tournament) {
        if (tournament == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

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
