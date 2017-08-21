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

    TournamentEvent pickNth(Set<TournamentEvent> set, int n) {
        def iterator = set.iterator()
        TournamentEvent event
        (n + 1).times {
            if (iterator.hasNext()) {
                event = iterator.next()
            }
        }
        return event
    }

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
                    registrationDetails: """<p>Inscription sur <a href="https://play.eslgaming.com/france/">le site de l'ESL</a>. </p>""",
                    rules: "<p>Please refer to the ESL website for more detailed rules</p>" +
                            "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam aliquet lacinia massa, vitae scelerisque velit luctus eget. Mauris venenatis, nunc at sollicitudin volutpat, sem lorem pellentesque mi, id hendrerit augue ipsum quis ligula. In purus elit, suscipit vitae nulla sit amet, placerat tincidunt erat. Cras vitae pulvinar velit. Suspendisse eu faucibus dolor, sed congue leo. Proin a aliquam tellus. Aliquam et enim in ipsum molestie posuere rhoncus eu erat. Fusce sodales vel tortor in congue. Nullam vulputate gravida maximus. Duis vitae ante elementum, laoreet leo quis, faucibus turpis. In malesuada, massa a euismod suscipit, velit elit elementum velit, vel ornare libero ante et lacus. Donec lectus odio, euismod eu luctus vitae, finibus ultrices augue. Pellentesque finibus pretium neque, non laoreet lacus condimentum eu. Praesent orci urna, molestie eu nulla vel, venenatis pharetra erat.</p>" +
                            "<p>Donec nec urna venenatis, malesuada felis id, eleifend odio. Duis vel orci non diam porta tempus. Vivamus efficitur risus sed libero mattis suscipit. Phasellus tristique risus eu nisl pellentesque, et vehicula nisi finibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent tincidunt, purus lobortis fermentum vehicula, sem ipsum molestie metus, vel hendrerit lacus neque id orci. Vivamus id velit ut metus dapibus feugiat vitae ut tortor. Suspendisse egestas lobortis luctus. Suspendisse eleifend non dolor ac rutrum. Integer leo magna, facilisis a pharetra eget, ultricies sit amet ligula. Etiam eget iaculis nunc.</p>" +
                            "<p>Praesent et mauris pulvinar, malesuada ex eget, eleifend nisl. Quisque varius dolor tempus leo facilisis, vitae faucibus nibh luctus. Phasellus pellentesque justo in dui dictum, at sollicitudin nisi vulputate. Curabitur eget odio efficitur, consequat lectus at, maximus diam. Pellentesque non nunc congue, aliquet magna id, varius enim. Maecenas non accumsan diam, ac malesuada lectus. Sed posuere ante pretium faucibus dictum. Ut nec ipsum posuere, ultrices diam ac, lobortis est.</p>" +
                            "<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer dignissim turpis ex, eget luctus elit finibus nec. Suspendisse sollicitudin interdum lorem sed finibus. Morbi molestie ex et dolor vestibulum, ac feugiat turpis tempor. Duis ut tortor sollicitudin sapien maximus scelerisque a in neque. Duis ullamcorper placerat nunc. Integer commodo vehicula ex et posuere. Integer pharetra lorem neque, aliquam malesuada nulla sagittis sed. Integer malesuada, urna id auctor pretium, ligula sapien posuere libero, eget posuere lacus turpis id libero. Donec a nibh sed urna consequat lobortis vitae a est. Proin mauris orci, ultrices et tincidunt at, vestibulum ut nunc. Quisque semper at tellus vel condimentum. Fusce dignissim posuere est, ut aliquet urna maximus id. Sed finibus hendrerit nunc sed dapibus. Curabitur laoreet, velit non finibus tristique, sem nisi malesuada quam, vitae fringilla turpis augue non sem. Aliquam efficitur diam at nulla pellentesque, sed dapibus est sodales.</p>" +
                            "<p>Nulla convallis ex eu felis semper, nec maximus nulla venenatis. Sed vel turpis in tortor congue suscipit eu a purus. Vivamus vehicula turpis a sagittis mollis. Integer pharetra hendrerit tortor, eget malesuada eros molestie finibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus est sit amet sem efficitur, vel sollicitudin orci tempor. Aenean id interdum massa. Curabitur blandit mauris vel velit laoreet cursus. Sed aliquam, mauris eget rhoncus ultricies, magna nulla congue turpis, ac vulputate sapien purus sit amet lacus. Nunc purus justo, tempus porta augue sed, sodales semper purus. Nunc pulvinar, ipsum rhoncus viverra sollicitudin, eros ligula convallis ipsum, iaculis tempor nunc felis eget diam. Aliquam erat volutpat. Morbi convallis tellus sed odio hendrerit, id sollicitudin lacus finibus. Aenean vitae elementum felis, eu tincidunt metus. Pellentesque porta ullamcorper justo sed eleifend.</p>" +
                            ""
            ).save()
            def firstTournamentDate = LocalDate.of(2018, Month.SEPTEMBER, 1)
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
                    .message('<p>Salut les Calamars !</p>\n' +
                    '\n' +
                    '<p>Envie de vivre une exp&eacute;rience hors du commun ? Tu souhaites rivaliser avec les meilleures Teams Fran&ccedil;aises, Europ&eacute;ennes et Mondiales ? Et bien sache que c&#39;est notre projet pour Splatoon 2.</p>\n' +
                    '\n' +
                    '<p>Si tu est sur InkZone pour rechercher une Team comp&eacute;titive sur Splatoon 2 sache que la Hearts Brothers est faite pour toi.&nbsp;<br />\n' +
                    'La Hearts Brothers (anciennement Jedox Star) est n&eacute;e le 25 Avril 2014 par KaporalSky au d&eacute;part pour Mario Kart 7, depuis 5 ans cette Team est pr&eacute;sente sur tous les jeux comp&eacute;titifs Nintendo (Kid Icarus Uprising, Mario Kart Wii, Super Smash Bros for 3DS et Wii U, Mario Kart 8, Splatoon et plus r&eacute;cemment Overwatch et Pok&eacute;mon Online).&nbsp;</p>\n' +
                    '\n' +
                    '<p>Actuellement la Hearts Brothers joue sur Mario Kart 8 Deluxe, elle est 1&egrave;re de la Division 3 de la MKU et &agrave; remport&eacute; le TFT MK8 #01, sur Splatoon 2 notre rooster est quasiment pr&ecirc;t, nous avons entre 6 et 7 joueurs actifs entre 16 et 25 ans et pour la plupart Parisien.&nbsp;<br />\n' +
                    '<br />\n' +
                    'Nous recherchons des joueurs avant tout motiv&eacute;s et pr&ecirc;ts &agrave; progresser afin de peaufiner une line up comp&eacute;titive, sachant que le jeu vient de sortir, nous n&#39;exigeons pas un rang particulier, mais une grande maturit&eacute;, de bonnes disponibilit&eacute;s ainsi qu&#39;une r&eacute;elle volont&eacute; de progresser.&nbsp;<br />\n' +
                    'Si tu souhaites nous rejoindre, cela se fait sur notre forum en 3 &eacute;tapes :&nbsp;http://hearts-brothers.clicforum.com/index.php<br />\n' +
                    '1) Inscription sur le forum<br />\n' +
                    '2) Pr&eacute;sentation &agrave; partir du mod&egrave;le dans la section appropri&eacute;e<br />\n' +
                    '3) Demande de recrutement &agrave; partir du mod&egrave;le qui se trouve dans la section.&nbsp;<br />\n' +
                    '<br />\n' +
                    'Vous pouvez nous contacter :&nbsp;<br />\n' +
                    '-Sur discord : Marteaufou#8054 ou&nbsp;H&szlig;★Robin [ebTV]#7069<br />\n' +
                    '<br />\n' +
                    'Suivez la Hearts Brothers sur nos r&eacute;seaux sociaux :</p>\n' +
                    '\n' +
                    '<p>-Twitter :&nbsp;https://twitter.com/heartsbrothers<br />\n' +
                    '-Youtube :&nbsp;https://www.youtube.com/channel/UCg663gni69sf0LNBckahMvg<br />\n' +
                    '-Twitch :&nbsp;https://www.twitch.tv/heartsbrothers<br />\n' +
                    '<br />\n' +
                    'A plus tard Calamars !</p>')
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

            def ad3 = RecruitingAd.builder()
                    .type(AdType.LOOKING_FOR_TEAMMATE_AD)
                    .title("Je monte une team pour le fun")
                    .message("Salut... ")
                    .author(ayo)
                    .rank(Rank.B)
                    .createdAt(Instant.now().plusSeconds(3600 * 24 * 5))
                    .build()
                    .save(failOnError: true)

            def cover = new Cover(
                    name: 'Splatoon 2',
                    url: 'https://s3-eu-central-1.amazonaws.com/splatoon/0be69296-2b43-4c19-8d2e-b567df7f5fdb-image.png'
            ).save(failOnError: true)

            def news1 = new News(
                    title: "News de test 1",
                    content: "<p>Content of the news</p>",
                    date: Instant.now().minus(2, ChronoUnit.DAYS),
                    cover: cover
            ).save(failOnError: true)

            def news2 = new News(
                    title: "News de test 2",
                    content: "<p>Content of the news</p>",
                    date: Instant.now(),
                    cover: cover
            ).save(failOnError: true)

            def news3 = new News(
                    title: "Des nouveautes concernant la creation de personnages",
                    content: """
                        <p style="text-align:center"><img src="https://s3-eu-central-1.amazonaws.com/splatoon/0be69296-2b43-4c19-8d2e-b567df7f5fdb-image.png" style="height:191px; width:310px" /></p>
                        <p style="text-align:center">&nbsp;</p>
                        <p>Pas mal de nouveautes sont a venir sur Splatoon. Aujourd&#39;hui nous nous interessons a la creation de personnages...&nbsp;</p>
                    """,
                    date: Instant.now(),
                    cover: cover
            ).save(failOnError: true)

            def news4 = new News(
                    title: "News de test 4",
                    content: '<p>Content of the news</p><img src="https://s3.eu-central-1.amazonaws.com/splatoon/13e47e0a-f826-4ebb-9c12-b2f4c336c170-test.png" class="img-responsive"><hr>' +
                            '<p style="text-align:center"><iframe frameborder="0" height="378" scrolling="no" src="https://player.twitch.tv/?video=v156860792&amp;autoplay=false" width="620"></iframe></p>',
                    date: Instant.now(),
                    cover: cover
            ).save(failOnError: true)

            def teamRisingMoon = new Team(name: 'Rising Moon').save(failOnError: true)
            def teamSeiches = new Team(name: 'Les seiches').save(faileOnError: true)

            new Ladder(
                    event: pickNth(tournament.events, 0),
                    team: teamRisingMoon,
                    points: 50,
                    wins: 3,
                    loses: 0,
                    date: Instant.now().minus(60, ChronoUnit.DAYS)
            ).save()

            new Ladder(
                    event: pickNth(tournament.events, 1),
                    team: teamRisingMoon,
                    points: 35,
                    wins: 2,
                    loses: 1,
                    date: Instant.now()
            ).save()

            new Ladder(
                    event: pickNth(tournament.events, 1),
                    team: teamSeiches,
                    points: 50,
                    wins: 3,
                    loses: 0,
                    date: Instant.now()
            ).save()

            def category1 = new ArticleCategory(name: 'Analyze de match').save()
            def category2 = new ArticleCategory(name: 'Gears').save()
            def category3 = new ArticleCategory(name: 'Interview').save()

            def article1 = new Article(
                    title: "La finale du Go 4 Splatoon",
                    content: "<p>Contenu de test</p>",
                    cover: cover,
                    category: category1,
                    date: Instant.now()
            ).save(failOnError: true)

            def article2 = new Article(
                    title: "Hime et PA répondent à vos questions",
                    content: "<p>Contenu de test</p>",
                    cover: cover,
                    category: category3,
                    date: Instant.now()
            ).save(failOnError: true)

            def article3 = new Article(
                    title: "Interview Rising Moon",
                    content: "<p>Contenu de test</p>",
                    cover: cover,
                    category: category3,
                    date: Instant.now()
            ).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
