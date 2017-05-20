<%@ page import="splatoon.AdType; splatoon.TournamentOrganizer; splatoon.TournamentEvent; splatoon.RecruitingAd" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Portail Splatoon France</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<div class="home-page">

    <div class="streams">
        <div class="row">
            <div class="col-sm-6">
                <g:if test="${streams.size() == 0}">
                    <div class="twitch-placeholder">
                    </div>
                </g:if>
                <g:if test="${streams.size() > 0}">
                    <div class="twitch-stream">
                        <iframe src="/twitch_placeholder"
                                id="twitch"
                                name="twitch"
                                frameborder="0"
                                allowfullscreen="true"
                                scrolling="no"></iframe>
                    </div>
                </g:if>


            </iframe>
            </div>

            <div class="col-sm-6">
                <p>Live Streams:</p>
                <ul>
                    <g:each in="${streams}" var="stream">
                        <li class="stream-switch" data-channel-name="${stream.channelName}">
                            <a href="https://player.twitch.tv/?autoplay=true&channel=${stream.channelName}" target="twitch">
                                ${stream.channelName}
                            </a>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
    %{--<div class="row">--}%
    %{--<div class="next-events col-sm-6 col-xs-12">--}%
    %{--<g:render template="tournamentEvent/nextEvents"--}%
    %{--model="${[nextEvents: TournamentEvent.findUpcomingEvents(6)]}"/>--}%
    %{--</div>--}%

    %{--<div class="news col-sm-6 col-xs-12">--}%
    %{--<g:panel title="Les dernières nouvelles">--}%
    %{--<p>Aucun contenu pour le moment.</p>--}%
    %{--</g:panel>--}%
    %{--</div>--}%
    %{--</div>--}%

    <div class="featured-news">
        <h2>A la Une</h2>
        <g:render template="/news/latest_news"/>
    </div>

    <div class=" recruitment row">
        <h2>Recrutements & Candidatures</h2>

        <div class="row">
            <div class="col-sm-6">
                <h3>Recherches de joueurs</h3>
                <ul class="ads">
                    <g:each in="${RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAMMATE_AD)}" var="ad">
                        <li class="ad">
                            <h4 class="title">${ad.title}</h4>

                            <p class="intro">${ad.message}</p>

                            <div class="read-more">
                                <g:link mapping="recruitment_show_ad" id="${ad.id}">
                                    en lire +
                                </g:link>
                            </div>
                        </li>
                    </g:each>
                </ul>
            </div>

            <div class="col-sm-6">
                <h3>Recherches d'equipes</h3>
                <ul>
                    <g:each in="${RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAM_AD)}" var="ad">
                        <li class="ad">
                            <h4 class="title">${ad.title}</h4>

                            <p class="intro">
                                <g:excerptHtml code="${ad.shortMessage}" strict="true"/>
                            </p>

                            <div class="read-more">
                                <g:link mapping="recruitment_show_ad" id="${ad.id}">
                                    en lire +
                                </g:link>
                            </div>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>
