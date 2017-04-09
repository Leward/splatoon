package splatoon.website

import grails.util.Environment
import splatoon.*

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            log.debug("Development environment detected: boostrapping with some data.")
            // Create some tournament and events for the ESL organization
            def esl = new TournamentOrganizer(name: "ESL France", website: "https://play.eslgaming.com/france/").save()
            def tournament = new Tournament(name: "Nintendo Europe 2018", organizer: esl).save()
            def firstTournamentDate = LocalDate.of(2018, Month.JUNE, 1)
            10.times {
                new TournamentEvent(tournament: tournament, date: firstTournamentDate.plusDays(it), startTime: LocalTime.of(17, 00), endTime: LocalTime.of(20, 00)).save()
            }
            new TournamentOrganizer(name: "Splatoon FR", website: "http://splatoonfr.net").save();

            // SOG
            def sog = new TournamentOrganizer(name: "SoG", website: "http://societyofgamers.fr/").save()
            new Tournament(
                    name: "Splattour of Gods 2 Winer Bracket",
                    organizer: sog,
                    events: [
                            new TournamentEvent(
                                    date: LocalDate.of(2017, Month.MARCH, 10),
                                    startTime: LocalTime.of(21, 00),
                                    endTime: LocalTime.of(23, 59),
                                    streamUrl: "https://gaming.youtube.com/watch?v=TRo_iSNqNno",
                                    challongeUrl: "http://sogfr.challonge.com/fr/SplatofGods2"
                            )
                    ]
            ).save()

            def ayo = new User(
                    username: "Ayo",
                    password: 'changeit',
                    email: 'ayo@splatoon.fr'
            ).save()

            def oli = new User(
                    username: "Oli",
                    password: 'changeit',
                    email: 'oli@splatoon.fr'
            ).save()

            def ad1 = RecruitingAd.builder()
                    .type(AdType.LOOKING_FOR_TEAM_AD)
                    .title("Recherche une team casual")
                    .message("Hello")
                    .author(ayo)
                    .profileUrl('https://twitter.com/Rising_Moon_Sp/')
                    .createdAt(Instant.now())
                    .build()
                    .save(failOnError: true)

            def ad2 = RecruitingAd.builder()
                    .type(AdType.LOOKING_FOR_TEAMMATE_AD)
                    .title("Recherche joueurs S+ pour team competitive")
                    .message("Salut... ")
                    .author(oli)
                    .createdAt(Instant.now())
                    .build()
                    .save(failOnError: true)
        }
    }
    def destroy = {
    }
}
