package splatoon

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime
import java.time.Month

class PerformanceSpec extends Specification {

    static Team TEAM_A = new Team(name: 'Team A')
    static TournamentOrganizer TO_A = new TournamentOrganizer(name: "TO A").withId(100)
    static TournamentEvent EVENT_TOURNAMENT_A = new TournamentEvent(
            tournament: new Tournament(name: 'Tournament A', organizer: TO_A).withId(1),
            date: LocalDate.of(2015, Month.APRIL, 15),
            startTime: LocalTime.MIN,
            endTime: LocalTime.MAX
    )

    def "should_add_ladder_entry"() {
        given:
        def performance = new Performance()
        def ladder = new Ladder(team: TEAM_A, points: 9, wins: 3, loses: 0, event: EVENT_TOURNAMENT_A)
        when:
        performance.add(ladder)
        then:
        performance == new Performance(points: 9, wins: 3, loses: 0)
    }


    def "test equals"() {
        expect:
        new Performance(wins: 5, loses: 3, points: 15) == new Performance(wins: 5, loses: 3, points: 15)
    }

    def "should calculate ratio"() {
        setup:
        def performance = new Performance(wins: wins, loses: loses)
        expect:
        performance.ratioAsString == ratio
        where:
        wins | loses | ratio
        0    | 0     | '0'
        1    | 0     | '1'
        0    | 1     | '0'
        1    | 1     | '0.5'
        6    | 1     | '0.86'

    }

}
