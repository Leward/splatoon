package splatoon

import org.springframework.validation.Errors

import java.time.Instant

class Ladder {

    TournamentEvent event
    Team team
    Instant date = Instant.now()
    int points
    int wins
    int loses

    static constraints = {
        event(nullable: false)
        team(nullable: false, validator: Ladder.ensureOneTeamResultPerEventValidator)
        date(nullable: false)
        points(min: 0)
    }

    static ensureOneTeamResultPerEventValidator =  { val, Ladder obj, Errors errors ->
        def similarResults = Ladder.findAllByEventAndTeam(obj.event, obj.team)
        boolean valid = obj.id == null ? similarResults.size() == 0 : similarResults.size() == 1
        if(!valid) {
            errors.rejectValue("team","duplicated ladder entry", "Une équipe a déjà un résultat enregistré sur ce tournoi")
        }
    }

    static mapping = {
        event(cascade: 'none')
        team(cascade: 'none', lazy: false)
    }

    MonthYear getMonthYear() {
        return new MonthYear(event.date.month, event.date.year)
    }
}
