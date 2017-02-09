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
        startTime(nullable: false, validator: { startTime, tournamentEvent -> startTime.isBefore(tournamentEvent.endTime) })
        endTime(nullable: false)
    }

    static List<TournamentEvent> findUpcomingEvents(Integer limit = -1) {
        def queryConfig = [:]
        if (limit > 0) {
            queryConfig.max = limit
        }
        return findAllByDateGreaterThanEquals(LocalDate.now(), queryConfig)
    }

}
