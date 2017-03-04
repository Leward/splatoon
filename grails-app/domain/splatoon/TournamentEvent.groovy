package splatoon

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
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

    String challongeUrl
    String streamUrl

    static belongsTo = [tournament: Tournament]

    static mapping = {
        sort(date: "asc", startTime: "asc")
    }

    static constraints = {
        date(nullable: false)
        endTime(nullable: false)
        challongeUrl(url: true, nullable: true)
        streamUrl(url: true, nullable: true)
    }

    static embedded = ['']

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

    boolean isYoutubeGamingStream() {
        return streamUrl != null && streamUrl.startsWith("https://gaming.youtube.com/watch")
    }

    String extractYoutubeStreamId() {
        if(!isYoutubeGamingStream()) {
            return null
        }
        def matches = streamUrl =~ /^https:\/\/gaming.youtube.com\/.*v=(\w+).*/
        println matches
        return matches[0][1]
    }

    String extractTwitchStreamId() {
        if(!isTwitchStream()) {
            return null
        }
        def matches = streamUrl =~ /^https:\/\/www.twitch.tv\/(\w+)/
        return matches[0][1]
    }

    boolean isTwitchStream() {
        return streamUrl != null && streamUrl.startsWith("https://www.twitch.tv/")
    }

}
