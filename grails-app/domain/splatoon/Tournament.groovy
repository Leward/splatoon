package splatoon

import java.time.LocalDate

class Tournament {

    String name
    TournamentOrganizer organizer
//    LocalDate startDate
//    LocalDate endDate

    static constraints = {
        organizer(nullable: false)
//        startDate(
//                nullable: false,
//                validator: { startDate, tournamentOrganizer -> startDate.isBefore(endDate) }
//        )
//        endDate(nullable: false)
    }

    @Override
    String toString() {
        return name
    }
}
