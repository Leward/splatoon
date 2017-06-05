package splatoon

import spock.lang.Specification

class RankedTeamsSpec extends Specification {

    static Team TEAM_A = new Team(name: 'Team A')
    static Team TEAM_B = new Team(name: 'Team B')

    def "should rank the teams"() {
        given:
        def ladders = [
                new Ladder(team: TEAM_A, points: 10),
                new Ladder(team: TEAM_B, points: 8),
                new Ladder(team: TEAM_B, points: 5)
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

}
