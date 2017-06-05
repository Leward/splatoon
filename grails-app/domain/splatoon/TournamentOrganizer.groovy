package splatoon

import java.time.LocalDate

class TournamentOrganizer {

    String name
    String website

    static hasMany = [tournaments: Tournament]

    static constraints = {
        name(blank: false)
        website(url: true)
    }

    @Override
    String toString() {
        return name
    }
}
