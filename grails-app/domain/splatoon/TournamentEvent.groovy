package splatoon

import org.apache.commons.validator.routines.UrlValidator
import org.springframework.validation.Errors

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TournamentEvent {

    public static final PARIS_ZONE_ID = ZoneId.of("Europe/Paris")
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

    /**
     * Whether the registrations to this events are managed via Inkzone
     */
    boolean managedRegistrations

    /**
     * Are registrations open for this event?
     */
    boolean registrationsOpen

    static belongsTo = [tournament: Tournament]
    static hasMany = [ladderEntries: Ladder, registrations: TournamentRegistration]

    static mapping = {
        sort(date: "asc", startTime: "asc")
        tournament fetch: 'join'
    }

    static constraints = {
        tournament()
        date(nullable: false)
        startTime()
        endTime(nullable: false)
        managedRegistrations()
        registrationsOpen()
        // Removed constraint url: true because of some error with FieldsPlugin since Grails 3.3
        challongeUrl(nullable: true, validator: { it == null || new UrlValidator().isValid(it) })
        streamUrl(nullable: true, validator: { it == null || new UrlValidator().isValid(it) })
    }

    static embedded = ['']

    static List<TournamentEvent> findUpcomingEvents(Integer limit = -1) {
        def queryConfig = [:]
        if (limit > 0) {
            queryConfig.max = limit
        }
        return findAllByDateGreaterThanEquals(LocalDate.now(ZoneId.of("Europe/Paris")), queryConfig)
    }

    static Optional<TournamentEvent> getLiveEvent() {
        def event = TournamentEvent.findAllByDate(LocalDate.now(ZoneId.of("Europe/Paris")))
                .find { it.isLive() }
        return Optional.ofNullable(event)
    }

    boolean isLive() {
        def todaysDate = LocalDate.now(PARIS_ZONE_ID)
        def nowTime = LocalTime.now(PARIS_ZONE_ID)
        return todaysDate == date &&
                startTime.isBefore(nowTime) &&
                endTime.isAfter(nowTime)
    }

    @Override
    String toString() {
        return tournament.name + ': ' + formattedDate
    }

    String getFormattedDate() {
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

    Instant getStartInstant() {
        def zoneOffset = PARIS_ZONE_ID.getRules().getOffset(Instant.now())
        return date.atTime(startTime).toInstant(zoneOffset)
    }

    boolean hasStarted() {
        return Instant.now().isAfter(startInstant)
    }

    /**
     * Can the general public (the players) register to this tournament?
     * @return whether the tournament is open to public registration at the moment of the moment call (now
     */
    boolean isPublicRegistrationOpen() {
        return registrationsOpen && managedRegistrations && !hasStarted()
    }

}
