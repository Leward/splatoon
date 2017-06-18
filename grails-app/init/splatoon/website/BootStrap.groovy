package splatoon.website

import com.fasterxml.jackson.core.JsonProcessingException
import com.mashape.unirest.http.ObjectMapper
import com.mashape.unirest.http.Unirest
import grails.util.Environment
import splatoon.*

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.temporal.ChronoUnit

class BootStrap {

    def init = { servletContext ->

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            def <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        def roleAdmin = Role.findOrSaveWhere(authority: Role.ROLE_ADMIN)
        def roleTO = Role.findOrSaveWhere(authority: Role.ROLE_TO)
        def roleModerator = Role.findOrSaveWhere(authority: Role.ROLE_MODERATOR)
        def roleEditor = Role.findOrSaveWhere(authority: Role.ROLE_EDITOR)

        if (Environment.current == Environment.DEVELOPMENT) {
            log.debug("Development environment detected: boostrapping with some data.")
            // Create some tournament and events for the ESL organization
            def esl = new TournamentOrganizer(name: "ESL France", website: "https://play.eslgaming.com/france/").save()
            def tournament = new Tournament(
                    name: "Nintendo Europe 2018",
                    organizer: esl,
                    game: Game.SPLATOON_1,
                    rules: "<p>Please refer to the ESL website for more detailed rules</p>"
            ).save()
            def firstTournamentDate = LocalDate.of(2018, Month.JUNE, 1)
            5.times {
                new TournamentEvent(
                        tournament: tournament,
                        date: firstTournamentDate.plusDays(it),
                        startTime: LocalTime.of(17, 00),
                        endTime: LocalTime.of(20, 00),
                        streamUrl: "https://gaming.youtube.com/watch?v=TRo_iSNqNno",
                        challongeUrl: "http://sogfr.challonge.com/fr/SplatofGods2"
                ).save()
            }
            new TournamentOrganizer(name: "Splatoon FR", website: "http://splatoonfr.net").save();

            // SOG
            def sog = new TournamentOrganizer(name: "SoG", website: "http://societyofgamers.fr/").save()
            new Tournament(
                    name: "Splattour of Gods 2 Winer Bracket",
                    organizer: sog,
                    events: [
                            new TournamentEvent(
                                    date: LocalDate.of(2017, Month.JULY, 10),
                                    startTime: LocalTime.of(21, 00),
                                    endTime: LocalTime.of(23, 59),
                                    streamUrl: "https://gaming.youtube.com/watch?v=TRo_iSNqNno",
                                    challongeUrl: "http://sogfr.challonge.com/fr/SplatofGods2"
                            )
                    ]
            ).save(failOrError: true)

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

            def admin = new User(
                    username: 'admin',
                    password: 'admin',
                    email: 'admin@splatoon.fr'
            ).save()

            new UserRole(user: admin, role: roleAdmin).save()

            def ad1 = RecruitingAd.builder()
                    .type(AdType.LOOKING_FOR_TEAM_AD)
                    .title("Recherche une team casual")
                    .message("Hello")
                    .author(ayo)
                    .profileUrl('https://twitter.com/Rising_Moon_Sp/')
                    .rank(Rank.C_PLUS)
                    .createdAt(Instant.now())
                    .build()
                    .save(failOnError: true)

            def ad1Reply1 = new AdReply(
                    ad: ad1,
                    author: oli,
                    message: '<p>Personne ?<p>',
                    createdAt: Instant.now()
            ).save(failOnError: true)

            def ad1Reply2 = new AdReply(
                    ad: ad1,
                    author: ayo,
                    message: '<p>No arnaque, no noob !<p>',
                    createdAt: Instant.now()
            ).save(failOnError: true)

            def ad2 = RecruitingAd.builder()
                    .type(AdType.LOOKING_FOR_TEAMMATE_AD)
                    .title("Recherche joueurs S+ pour team competitive")
                    .message("Salut... ")
                    .author(oli)
                    .rank(Rank.S_PLUS)
                    .createdAt(Instant.now())
                    .build()
                    .save(failOnError: true)

            def news1 = new News(
                    title: "News de test 1",
                    content: "<p>Content of the news</p>",
                    date: Instant.now().minus(2, ChronoUnit.DAYS)
            ).save(failOnError: true)

            def news2 = new News(
                    title: "News de test 2",
                    content: "<p>Content of the news</p>",
                    date: Instant.now()
            ).save(failOnError: true)

            def news3 = new News(
                    title: "Des nouveautes concernant la creation de personnages",
                    content: """
                        <p style="text-align:center"><img src="https://s3-eu-central-1.amazonaws.com/splatoon/0be69296-2b43-4c19-8d2e-b567df7f5fdb-image.png" style="height:191px; width:310px" /></p>
                        <p style="text-align:center">&nbsp;</p>
                        <p>Pas mal de nouveautes sont a venir sur Splatoon. Aujourd&#39;hui nous nous interessons a la creation de personnages...&nbsp;</p>
                    """,
                    date: Instant.now()
            ).save(failOnError: true)

            def news4 = new News(
                    title: "News de test 4",
                    content: "<p>Content of the news</p>",
                    date: Instant.now()
            ).save(failOnError: true)

            def teamRisingMoon = new Team(name: 'Rising Moon').save(failOnError: true)
            def teamSeiches = new Team(name: 'Les seiches').save(faileOnError: true)

            new Ladder(
                    event: tournament.events.get(0),
                    team: teamRisingMoon,
                    points: 50,
                    wins: 3,
                    loses: 0,
                    date: Instant.now().minus(60, ChronoUnit.DAYS)
            ).save()

            new Ladder(
                    event: tournament.events.get(1),
                    team: teamRisingMoon,
                    points: 35,
                    wins: 2,
                    loses: 1,
                    date: Instant.now()
            ).save()

            new Ladder(
                    event: tournament.events.get(1),
                    team: teamSeiches,
                    points: 50,
                    wins: 3,
                    loses: 0,
                    date: Instant.now()
            ).save()
        }
    }
    def destroy = {
    }
}
