package splatoon

import org.springframework.validation.Errors

import java.time.Instant

class TournamentRegistration {

    Team team
    TournamentEvent event
    User registeredBy
    Instant registeredAt

    static constraints = {
        team nullable: false, validator: TournamentRegistration.ensureOneRegistrationPerTeamPerEventValidator
        event nullable: false
    }

    static ensureOneRegistrationPerTeamPerEventValidator =  { val, TournamentRegistration obj, Errors errors ->
        def similarResults = TournamentRegistration.findAllByEventAndTeam(obj.event, obj.team)
        boolean valid = obj.id == null ? similarResults.size() == 0 : similarResults.size() == 1
        if(!valid) {
            errors.rejectValue("team","duplicated registration entry", "L'équipe est deja enregistree sur ce tournoi")
        }
    }

    def beforeInsert() {
        registeredAt = Instant.now()
    }
}
