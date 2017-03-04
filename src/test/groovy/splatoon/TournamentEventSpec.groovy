package splatoon

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TournamentEvent)
class TournamentEventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "should extract twitch stream id"() {
        setup:
            def event = new TournamentEvent(streamUrl: "https://www.twitch.tv/ko_gma")
        expect:
            event.extractTwitchStreamId() == "ko_gma"
    }

    void "should extract youtube gaming stram id"() {
        setup:
            def event = new TournamentEvent(streamUrl: "https://gaming.youtube.com/watch?v=CtyufglAhRY")
        expect:
            event.extractYoutubeStreamId() == "CtyufglAhRY"
    }
}
