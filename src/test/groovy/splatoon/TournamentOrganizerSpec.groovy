package splatoon

import spock.lang.Specification

class TournamentOrganizerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test equality"() {
        given:
        def TO_A = new TournamentOrganizer(name: "TO A").withId(100)
        def TO_B = new TournamentOrganizer(name: "TO B").withId(200)
        expect:
        TO_A != TO_B
    }

    void "should find by ID"() {
        given:
        def TO_A = new TournamentOrganizer(name: "TO A").withId(100)
        def TO_B = new TournamentOrganizer(name: "TO B").withId(200)
        def list = [TO_A, TO_B]
        expect:
        list.find { it.id == 200 } == TO_B
        list.find { it.id == 100 } == TO_A
    }
}
