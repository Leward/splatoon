package splatoon

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TournamentEvent {

    LocalDate date
    LocalTime startTime
    LocalTime endTime

    static belongsTo = [tournament: Tournament]

    static mapping = {
        sort(date: "asc", startTime: "asc")
    }

    static constraints = {
        date(nullable: false)
        endTime(nullable: false)
    }

    static List<TournamentEvent> findUpcomingEvents(Integer limit = -1) {
        def queryConfig = [:]
        if (limit > 0) {
            queryConfig.max = limit
        }
        return findAllByDateGreaterThanEquals(LocalDate.now(), queryConfig)
    }

    @Override
    String toString() {
        return date.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.FRANCE))
    }

}
