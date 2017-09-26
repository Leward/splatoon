package splatoon

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

class RankingsCompilationTest extends Specification {

    static Team TEAM_A = new Team(name: 'Team A')
    static Team TEAM_B = new Team(name: 'Team B')

    static TournamentOrganizer TO_A = new TournamentOrganizer(name: "TO A").withId(100)
    static TournamentOrganizer TO_B = new TournamentOrganizer(name: "TO B").withId(200)

    static TournamentEvent EVENT_TOURNAMENT_A = new TournamentEvent(
            tournament: new Tournament(name: 'Tournament A', organizer: TO_A).withId(1),
            date: LocalDate.now(),
            startTime: LocalTime.MIN,
            endTime: LocalTime.MAX
    )
    static TournamentEvent EVENT_TOURNAMENT_B = new TournamentEvent(
            tournament:  new Tournament(name: 'Tournament B', organizer: TO_B).withId(1),
            date: LocalDate.now(),
            startTime: LocalTime.MIN,
            endTime: LocalTime.MAX
    )

    def "should rank the teams per TO"() {
        given:
        def ladders = [
                new Ladder(team: TEAM_A, points: 9, wins: 3, loses: 0, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_B, points: 3, wins: 0, loses: 3, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_A, points: 3, wins: 0, loses: 3, event: EVENT_TOURNAMENT_B),
                new Ladder(team: TEAM_B, points: 9, wins: 3, loses: 0, event: EVENT_TOURNAMENT_B)
        ]
        when:
        def rankings = new RankingsCompilation(ladders)
        then:
        rankings.perTournamentOrganizerRankings[TO_A].list[0].team == TEAM_A
        rankings.perTournamentOrganizerRankings[TO_A].list[1].team == TEAM_B
        rankings.perTournamentOrganizerRankings[TO_B].list[0].team == TEAM_B
        rankings.perTournamentOrganizerRankings[TO_B].list[1].team == TEAM_A
    }

}
