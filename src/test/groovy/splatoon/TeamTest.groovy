package splatoon

import spock.lang.Specification

class TeamTest extends Specification {

    def "two new objects without id should be different"() {
        expect:
            a != b
            a.hashCode() != b.hashCode()
        where:
        a << [new Team(name: 'TEAM A'), new Team(name: 'TEAM A')]
        b << [new Team(name: 'TEAM A'), new Team(name: 'TEAM B')]
    }

}
