package splatoon.elo

import splatoon.Team
import spock.lang.Specification

class TeamEloRankingSpec extends Specification {

    def "two entries of same score are different"() {
        given:
        def teamA = new Team(id: 1, name: "Team A")
        def teamB = new Team(id: 2, name: "Team B")
        def a = new TeamEloRanking(teamA, 1500)
        def b = new TeamEloRanking(teamB, 1500)
        expect:
        a != b
    }

    def "two entries of different scores are different"() {
        given:
        def teamA = new Team(id: 1, name: "Team A")
        def teamB = new Team(id: 2, name: "Team B")
        def a = new TeamEloRanking(teamA, 1200)
        def b = new TeamEloRanking(teamB, 1500)
        expect:
        a != b
    }

    def "sorting using comparator"() {
        given:
        def a = new TeamEloRanking(new Team(id: 1, name: "Team A"), 1200)
        def b = new TeamEloRanking(new Team(id: 2, name: "Team B"), 1700)
        def c = new TeamEloRanking(new Team(id: 3, name: "Team C"), 1100)
        expect:
        def list = [a, b, c]
        list.sort(TeamEloRanking.COMPARATOR)
        list == [b, a, c]
    }

}
