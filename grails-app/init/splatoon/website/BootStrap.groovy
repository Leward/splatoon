package splatoon.website

import grails.util.Environment
import splatoon.Tournament
import splatoon.TournamentEvent
import splatoon.TournamentOrganizer

import java.time.LocalDate
import java.time.LocalTime
import java.time.Month

class BootStrap {

    def init = { servletContext ->
        if(Environment.current == Environment.DEVELOPMENT) {
            log.debug("Development environment detected: boostrapping with some data.")
            // Create some tournament and events for the ESL organization
            def esl = new TournamentOrganizer(name: "ESL France", website: "https://play.eslgaming.com/france/").save()
            def tournament = new Tournament(name: "Nintendo Europe 2018", organizer: esl)
            def firstTournamentDate = LocalDate.of(2018, Month.JUNE, 1)
            10.times {
                new TournamentEvent(tournament: tournament, date: firstTournamentDate.plusDays(it), startTime: LocalTime.of(17, 00), endTime: LocalTime.of(20, 00)).save()
            }
            new TournamentOrganizer(name: "Splatoon FR", website: "http://splatoonfr.net").save();
        }
    }
    def destroy = {
    }
}
