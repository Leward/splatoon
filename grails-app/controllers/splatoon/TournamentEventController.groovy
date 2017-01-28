package splatoon

import java.time.LocalDate

class TournamentEventController {

    static scaffold = TournamentEvent

    def showNextEvents() {
        def nextEvents = TournamentEvent.findAllByDateGreaterThanEquals(LocalDate.now())
        log.debug("Return ${nextEvents.size()} event(s) to come.")
        println("Return ${nextEvents.size()} event(s) to come.")
        render(view: "showNextEvents", model: [nextEvents: nextEvents])
    }

}
