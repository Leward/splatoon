package splatoon

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime
import java.time.Month

class PerformancesTest extends Specification {

    static Team TEAM_A = new Team(name: 'Team A')
    static TournamentOrganizer TO_A = new TournamentOrganizer(name: "TO A").withId(100)
    static TournamentEvent EVENT_TOURNAMENT_A = new TournamentEvent(
            tournament: new Tournament(name: 'Tournament A', organizer: TO_A).withId(1),
            date: LocalDate.of(2015, Month.APRIL, 15),
            startTime: LocalTime.MIN,
            endTime: LocalTime.MAX
    )
    static TournamentEvent EVENT_TOURNAMENT_B = new TournamentEvent(
            tournament: new Tournament(name: 'Tournament B', organizer: TO_A).withId(2),
            date: LocalDate.of(2015, Month.JUNE, 15),
            startTime: LocalTime.MIN,
            endTime: LocalTime.MAX
    )

    def "should_add_ladder_entry"() {
        given:
        def performances = new Performances()
        def ladder = new Ladder(team: TEAM_A, points: 9, wins: 3, loses: 0, event: EVENT_TOURNAMENT_A)
        when:
        performances.add(ladder)
        then:
        performances.map[new MonthYear(Month.APRIL, 2015)].points == 9
    }

    def "should not have gaps between months"() {
        given:
        def performances = new Performances()
        def ladderA = new Ladder(team: TEAM_A, points: 9, wins: 3, loses: 0, event: EVENT_TOURNAMENT_A)
        def ladderB = new Ladder(team: TEAM_A, points: 7, wins: 2, loses: 1, event: EVENT_TOURNAMENT_B)
        when:
        performances.add(ladderA)
        performances.add(ladderB)
        then:
        performances.map[new MonthYear(Month.MAY, 2015)] != null
    }

    def "should like a map using getAt method"() {
        given:
        def performances = new Performances()
        def ladder = new Ladder(team: TEAM_A, points: 9, wins: 3, loses: 0, event: EVENT_TOURNAMENT_A)
        when:
        performances.add(ladder)
        then:
        performances[new MonthYear(Month.APRIL, 2015)] != null
    }

}
