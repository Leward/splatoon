package splatoon

import grails.transaction.Transactional

class LadderController {

    def index() {
        def rankedTeams = new RankedTeams(Ladder.findAll())
        render(view: 'index', model: [rankedTeams: rankedTeams])
    }

    @Transactional
    def create() {
        def ladder = new Ladder(params)
        if(params.containsKey("eventId")) {
            ladder.event = TournamentEvent.get(params.getInt("eventId"))
        }
        if(request.isPost()) {
            if (ladder.validate()) {
                ladder.save()
                redirect(mapping: 'admin_ladder_event_details', id: ladder.event.id)
            }
        }
        render(view: 'create', model: [ladder: ladder])
    }

    @Transactional
    def update(Ladder ladder) {
        if(request.isPost() && ladder.validate()) {
            ladder.save()
            redirect(mapping: 'admin_ladder_event_details', id: ladder.event.id)
        }
        render(view: 'update', model: [ladder: ladder])
    }

    def adminEventDetails(TournamentEvent event) {
        render(view: 'admin_event_details', model: [event: event])
    }
}
