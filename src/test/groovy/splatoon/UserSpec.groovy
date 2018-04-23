package splatoon

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month;

@Integration
@Rollback
class UserSpec extends Specification {

    void "test equals"() {
        expect:
        (a == b) == shouldBeEqual
        where:
        a                                   | b                                 | shouldBeEqual
        new User()                          | null                              | false
        new User()                          | new User()                        | false
        new User(id: 1)                     | new User(id: 2)                   | false
        new User(id: 1, username: "Albert") | new User(id: 1, username: "Toto") | false
    }

    void "test persist and retrieve"() {
        given:
        new User(
                username: "Ayo",
                password: 'changeit',
                email: 'ayo@splatoon.fr',
                playerProfile: new PlayerProfile(
                        nintendoId: 'Ayoyo',
                        birthDate: LocalDate.of(1990, Month.AUGUST, 4),
                        mainWeaponCategory: MainWeaponCategory.BLASTER,
                        roles: [PlayerRole.FLANK, PlayerRole.SUPPORT],
                        availability: "Les mecredi et samedi soir",
                        alreadyInATeam: true,
                        lookingForATeam: false,
                        lookingForFunCompetition: true,
                        lookingForProCompetition: false)
            ).save(flush: true, failOnError: true)
        when:
        def ayo = User.findByUsername('Ayo')
        then:
        ayo.email == 'ayo@splatoon.fr'
        ayo.playerProfile.nintendoId == 'Ayoyo'
    }

}