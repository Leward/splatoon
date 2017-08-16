package splatoon

import spock.lang.Specification

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
