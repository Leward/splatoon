package splatoon

import groovy.transform.EqualsAndHashCode

import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@EqualsAndHashCode
class PlayerProfile {
    Rank rank = DEFAULT_RANK
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
    /**
     * Player presentation that is displayed in the user profile and when searching for people looking for teams.
     */
    String presentation

    Instant createdAt = Instant.now()
    Instant updatedAt = createdAt

    SortedSet<PlayerRole> roles = new TreeSet<>()

    static hasMany = [roles: PlayerRole]

    static belongsTo = [user: User]

    static constraints = {
        nintendoId nullable: true, size: 0..100
        birthDate nullable: true
        mainWeaponCategory nullable: true
        availability nullable: true
        presentation nullable: true
        user unique: true
    }

    static mapping = {
        lookingForATeam column: "looking_for_a_team"
        alreadyInATeam column: "already_in_a_team"
        rank nullable: false
    }

    static final Rank DEFAULT_RANK = Rank.C

    def beforeInsert() {
        createdAt = Instant.now()
        updatedAt = createdAt
    }

    def beforeUpdate() {
        updatedAt = Instant.now()
    }

    Integer getAge() {
        if(birthDate == null) {
            return null
        }
        def today = LocalDate.now(ZoneId.of("Europe/Paris"))
        return Period.between(birthDate, today).getYears()
    }

    boolean isLookingForCompetition() {
        return lookingForProCompetition || lookingForFunCompetition
    }

    Collection<String> getCompetitionTypes() {
        def types = []
        if(lookingForFunCompetition) {
            types.add("Fun")
        }
        if(lookingForProCompetition) {
            types.add("Pro")
        }
        return types
    }
}
