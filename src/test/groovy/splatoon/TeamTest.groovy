package splatoon

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TeamTest extends Specification implements DomainUnitTest<Team> {

    def "two teams with different id should be different"() {
        given:
        def teamA = new Team(id: 1, name: "Team A")
        def teamB = new Team(id: 2, name: "Team B")
        expect:
        teamA != teamB

    }

    def "two new objects without id should be different"() {
        expect:
            a != b
            a.hashCode() != b.hashCode()
        where:
        a << [new Team(name: 'TEAM A'), new Team(name: 'TEAM A')]
        b << [new Team(name: 'TEAM A'), new Team(name: 'TEAM B')]
    }

    def "validation of country code"() {
        expect:
            new Team(name: 'TEAN A', countryCode: countryCode).validate() == isValid
        where:
        countryCode | isValid
        'fr' | true
        'be' | true
        'eu' | true
        'pp' | false
        'bg' | false
    }

}
