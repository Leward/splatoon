package splatoon

class TournamentEventController {

    static scaffold = TournamentEvent

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

    // For admin
    def show(TournamentEvent tournamentEvent) {
        respond tournamentEvent
    }

}
