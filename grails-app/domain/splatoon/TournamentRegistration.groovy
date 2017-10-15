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

    boolean validatePublicRegistration(User currentUser) {
        validate()
        if(!team.canBeManagedBy(currentUser)) {
            log.info("Rejecting team ${team} for user ${currentUser} as he cannot manage the team")
            errors.rejectValue('team', 'team_registration.errors.not_team_leader', "Vous n'etes pas le leader de l'equipe.")
        }
        if(!event.isPublicRegistrationOpen()) {
            log.info("Rejecting event ${event} as public registration for that event is not open")
            errors.rejectValue('team', 'team_registration.errors.registration_closed', "Les inscriptions ne sont pas ouvertes pour cet evenement.")
        }
        return !hasErrors()
    }

    def beforeInsert() {
        registeredAt = Instant.now()
    }
}
