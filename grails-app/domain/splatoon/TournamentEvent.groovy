package splatoon

import java.time.LocalDate
import java.time.LocalTime

class TournamentEvent {

    LocalDate date
    LocalTime startTime
    LocalTime endTime
    Tournament tournament

    static mapping = {
        sort(date: "asc", startTime: "asc")
    }

    static constraints = {
        date(nullable: false)
        startTime(nullable: false, validator: {startTime, tournamentEvent -> startTime.isBefore(tournamentEvent.endTime)})
        endTime(nullable: false)
    }

    static List<TournamentEvent> findUpcomingEvents() {
        return findAllByDateGreaterThanEquals(LocalDate.now())
    }

}
