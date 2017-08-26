package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import  grails.gorm.transactions.Transactional
import org.springframework.security.access.AccessDeniedException

class LadderController {

    SpringSecurityService springSecurityService

    def index() {
        def rankedTeams = new RankedTeams(Ladder.findAll())
        render(view: 'index', model: [rankedTeams: rankedTeams])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    @Transactional
    def create() {
        def ladder = new Ladder(params)
        if(params.containsKey("eventId")) {
            ladder.event = TournamentEvent.get(params.getInt("eventId"))
        }
        if(request.isPost()) {
            verifyUserCanManage(ladder)
            if (ladder.validate()) {
                ladder.save()
                redirect(mapping: 'admin_ladder_event_details', id: ladder.event.id)
            }
        }
        render(view: 'create', model: [ladder: ladder])
    }

    private void verifyUserCanManage(Ladder ladder) {
        def user = springSecurityService.currentUser as User
        if (!user.canManage(ladder.event.tournament.organizer)) {
            throw new AccessDeniedException("User has no access to this tournament organizer")
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    @Transactional
    def update(Ladder ladder) {
        verifyUserCanManage(Ladder.get(ladder.id))
        if(request.isPost() && ladder.validate()) {
            verifyUserCanManage(ladder)
            ladder.save()
            redirect(mapping: 'admin_ladder_event_details', id: ladder.event.id)
        }
        render(view: 'update', model: [ladder: ladder])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def adminEventDetails(TournamentEvent event) {
        render(view: 'admin_event_details', model: [event: event])
    }

    @Secured(['ROLE_ADMIN'])
    @Transactional
    def reset() {
        if(request.method == 'POST') {
            Ladder.executeUpdate('DELETE FROM Ladder');
            flash.message = "Ladder remis à zéro"
        }
        redirect(mapping: 'admin_ladder')
    }
}
