package splatoon

import grails.gorm.DetachedCriteria
import grails.validation.Validateable
import org.grails.datastore.mapping.query.api.Criteria

import java.time.LocalDate
import java.time.ZoneId

class PlayerSearch implements Validateable {

    Rank minRank
    int minAge
    boolean funCompetition
    boolean proCompetition
    MainWeaponCategory mainWeaponCategory

    static constraints = {
        minRank nullable: true
        mainWeaponCategory nullable: true
        minAge required: false
    }

    DetachedCriteria<PlayerProfile> generateCriteria() {
        def query = PlayerProfile.where {
            lookingForATeam == true &&
            birthDate <= LocalDate.now(ZoneId.of("Europe/Paris")).minusYears(minAge)
        }
        if(minRank != null) {
            query = query.where { rank in minRank.getHigherOrEqualRanks() }
        }
        if (funCompetition) {
            query = query.where { lookingForFunCompetition == true }
        }
        if (proCompetition) {
            query = query.where { lookingForProCompetition == true }
        }
        if (mainWeaponCategory != null) {
            query = query.where { mainWeaponCategory == this.mainWeaponCategory }
        }
        return query
    }

//    Criteria generateCriteria() {
//        def builder = PlayerProfile.createCriteria()
//                .gt("birthDate", LocalDate.now(ZoneId.of("Europe/Paris")).minusYears(minAge))
//        if (funCompetition) {
//            builder.eq("lookingForFunCompetition", true)
//        }
//        if (proCompetition) {
//            builder.eq("lookingForProCompetition", true)
//        }
//        if (mainWeaponCategory != null) {
//            builder.eq("mainWeaponCategory", mainWeaponCategory)
//        }
//        return builder
//    }
}
