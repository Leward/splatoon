package splatoon

import spock.lang.Specification

class RankedTeamsSpec extends Specification {

    static Team TEAM_A = new Team(name: 'Team A')
    static Team TEAM_B = new Team(name: 'Team B')

    static TournamentEvent EVENT_TOURNAMENT_A = new TournamentEvent(tournament: new Tournament(id: 1, name: 'Tournament A'))
    static TournamentEvent EVENT_TOURNAMENT_B = new TournamentEvent(tournament:  new Tournament(id: 2, name: 'Tournament B'))

    def "should rank the teams"() {
        given:
        def ladders = [
                new Ladder(team: TEAM_A, points: 10, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_B, points: 8, wins: 2, loses: 1, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_B, points: 5, wins: 1, loses: 1, event: EVENT_TOURNAMENT_B)
        ]
        when:
        def rankedTeams = new RankedTeams(ladders)
        def ranking = rankedTeams.list
        then:
        ranking[0].team == TEAM_B
        ranking[0].rank == 1
        ranking[1].team == TEAM_A
        ranking[1].rank == 2
    }

    def "should add wins, loses and points"() {
        given:
        def ladders = [
                new Ladder(team: TEAM_A, points: 10, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_B, points: 8, wins: 2, loses: 1, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_B, points: 5, wins: 1, loses: 1, event: EVENT_TOURNAMENT_B)
        ]
        when:
        def teamBRanking = new RankedTeams(ladders).list.find { it.team == TEAM_B }
        then:
        assert teamBRanking.wins == 3
        assert teamBRanking.loses == 2
        assert teamBRanking.points == 13
    }

    def "should list the tournaments a team as participated to"() {
        given:
        def ladders = [
                new Ladder(team: TEAM_A, points: 10, event: EVENT_TOURNAMENT_A),
                new Ladder(team: TEAM_B, points: 8, wins: 2, loses: 1, event: EVENT_TOURNAMENT_B),
                new Ladder(team: TEAM_B, points: 5, wins: 1, loses: 1, event: EVENT_TOURNAMENT_A)
        ]
        when:
        def teamARanking = new RankedTeams(ladders).list.find { it.team == TEAM_A }
        def teamBRanking = new RankedTeams(ladders).list.find { it.team == TEAM_B }
        then:
        assert teamARanking.participations == [EVENT_TOURNAMENT_A.tournament] as Set
        assert teamARanking.nbTournaments == 1
        assert teamBRanking.participations == [EVENT_TOURNAMENT_A.tournament, EVENT_TOURNAMENT_B.tournament] as Set
        assert teamBRanking.nbTournaments == 2
    }

}
