package splatoon

import groovy.transform.EqualsAndHashCode

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@EqualsAndHashCode
class PlayerProfile {
    String nintendoId
    LocalDate birthDate
    MainWeaponCategory mainWeaponCategory
    boolean alreadyInATeam = false
    boolean lookingForATeam = false
    boolean lookingForFunCompetition = false
    boolean lookingForProCompetition = false
    /**
     * Description of when the player is usually available to play a game
     */
    String availability

    static hasMany = [roles: PlayerRole]

    static belongsTo = [user: User]

    static constraints = {
        nintendoId nullable: true, size: 0..100
        birthDate nullable: true
        mainWeaponCategory nullable: true
        availability nullable: true
        user unique: true
    }

    static mapping = {
        lookingForATeam column: "looking_for_a_team"
        alreadyInATeam column: "already_in_a_team"
    }

    Integer getAge() {
        if(birthDate == null) {
            return null
        }
        def today = LocalDate.now(ZoneId.of("Europe/Paris"))
        return Period.between(birthDate, today).getYears()
    }
}
