package splatoon

import spock.lang.Specification

class RankedTeamSpec extends Specification {

    def "should be sortable"() {
        given:
        def a = new RankedTeam(points: 85)
        def b = new RankedTeam(points: 50)
        when:
        def sorted = new TreeSet<RankedTeam>()
        sorted.add(a)
        sorted.add(b)
        def list = sorted.toList()
        then:
        list[0] == a
        list[1] == b

    }

    def "should calculate ratio"() {
        setup:
        def rankedTeam = new RankedTeam(wins: wins, loses: loses)
        expect:
        rankedTeam.ratioAsString == ratio
        where:
        wins | loses | ratio
        0    | 0     | '0'
        1    | 0     | '1'
        0    | 1     | '0'
        1    | 1     | '0.5'
        6    | 1     | '0.86'

    }

}
