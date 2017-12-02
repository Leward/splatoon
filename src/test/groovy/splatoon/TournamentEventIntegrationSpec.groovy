package splatoon

import grails.testing.gorm.DataTest
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

class TournamentEventIntegrationSpec extends Specification implements DataTest {

    @Override
    Class[] getDomainClassesToMock() {
        return [TournamentEvent, Tournament, TournamentOrganizer]
    }

    void "there should be a live event"() {
        setup:
        def to = new TournamentOrganizer(name: "TO A", website: 'https://google.com').save(failOnError: true)
        def tournament = new Tournament(organizer: to, name: 'A Tournament', rules: "Some rules", registrationDetails: "How to register... ").save(failOnError: true)
        def event = new TournamentEvent(
                tournament: tournament,
                date: LocalDate.now(ZoneId.of("Europe/Paris")),
                startTime: LocalTime.MIN,
                endTime: LocalTime.MAX).save(failOnError: true)
        expect:
        TournamentEvent.getLiveEvent().get().tournament.name == tournament.name
    }

    void "there should be no live event"() {
        setup:
        def to = new TournamentOrganizer(name: "TO A", website: 'https://google.com').save(failOnError: true)
        def tournament = new Tournament(organizer: to, name: 'A Tournament', rules: "Some rules", registrationDetails: "How to register... ").save(failOnError: true)
        def event = new TournamentEvent(
                tournament: tournament,
                date: LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(3),
                startTime: LocalTime.MIN,
                endTime: LocalTime.MAX).save(failOnError: true)
        expect:
        TournamentEvent.getLiveEvent().isPresent() == false
    }

}
