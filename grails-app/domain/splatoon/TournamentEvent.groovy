package splatoon

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TournamentEvent {

    LocalDate date

    /**
     * Start time of the Event in Paris timezone
     */
    LocalTime startTime

    /**
     * End time of the Event in Paris timezone
     */
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
        return findAllByDateGreaterThanEquals(LocalDate.now(ZoneId.of("Europe/Paris")), queryConfig)
    }

    boolean isLive() {
        def parisTimeZone = ZoneId.of("Europe/Paris");
        def todaysDate = LocalDate.now(parisTimeZone)
        def nowTime = LocalTime.now(parisTimeZone)
        return todaysDate == date &&
                startTime.isBefore(nowTime) &&
                endTime.isAfter(nowTime)
    }

    @Override
    String toString() {
        return date.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.FRANCE))
    }

}
