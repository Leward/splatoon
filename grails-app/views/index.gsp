<%@ page import="splatoon.HtmlStringUtils; splatoon.AdType; splatoon.TournamentOrganizer; splatoon.TournamentEvent; splatoon.RecruitingAd" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Portail e-sport Splatoon</title>
</head>

<body>

<div class="home-page">

    <div class="streams card">
        <main>
            <div class="row">
                <div class="col-sm-5 col-md-12 col-lg-6">
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

                </div>

                <div class="col-sm-7 col-md-12 col-lg-6">
                    <g:if test="${liveEvent.isPresent()}">
                        <div class="current-event-stream">
                            <div class="stream-preview">
                                <g:link mapping="tournament_event" id="${liveEvent.get().id}">
                                    <g:img file="live_user_example-80x45.jpg" alt="Live stream"/>
                                </g:link>
                            </div>

                            <div class="stream-details">
                                <g:link mapping="tournament_event" id="${liveEvent.get().id}">
                                    ${liveEvent.get().tournament.name}
                                </g:link>
                                <div class="current-event">
                                    <g:link mapping="tournament_event"
                                            id="${liveEvent.get().id}">event en cours</g:link>
                                </div>
                            </div>
                        </div>
                    </g:if>

                    <ul class="row">
                        <g:each in="${streams}" var="stream">
                            <li class="stream-switch col-xs-3" data-channel-name="${stream.channelName}">
                                <div class="channel-logo">
                                    <a href="https://player.twitch.tv/?autoplay=true&channel=${stream.channelName}"
                                       target="twitch">
                                        <g:if test="${stream.channelLogoUrl}">
                                            <img src="${stream.channelLogoUrl}" alt="${stream.channelName}"
                                                 class="img-circle">
                                        </g:if>
                                        <g:else>
                                            <div class="twitch-placeholder img-circle"></div>
                                        </g:else>
                                    </a>
                                </div>

                                <div class="channel-name">
                                    <a href="https://player.twitch.tv/?autoplay=true&channel=${stream.channelName}"
                                       target="twitch">
                                        ${stream.channelName}
                                    </a>
                                </div>

                            </li>
                        </g:each>
                    </ul>

                    <p class="text-right">
                        <g:link mapping="streams" class="more">+ de streamers</g:link>
                    </p>
                </div>
            </div>
        </main>
    </div>

    <div class="featured-news card">
        <header class="red"><h2>
            <g:link mapping="news">
                A la Une
                <span class="circle"></span>
            </g:link>
        </h2></header>
        <main><g:render template="/news/latest_news"/></main>
    </div>

    <div class="featured-news card">
        <header class="red"><h2>
            <g:link mapping="magazine">
                Magazine
                <span class="circle"></span>
            </g:link>
        </h2></header>
        <main><g:render template="/article/latest_articles"/></main>
    </div>

    <div class="recruitment card">
        <header class="blue"><h2>
            <g:link mapping="recruitment">
                Recrutements & Candidatures <span class="circle"></span>
            </g:link>
        </h2></header>

        <main>
            <div class="row">
                <div class="col-md-5 col-sm-6">
                    <h3>Recherches de joueurs</h3>
                    <ul class="ads">
                        <g:each in="${RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAMMATE_AD, [max: 6, sort: "createdAt", order: "desc"])}"
                                var="ad">
                            <li class="ad">
                                <div class="avatar">
                                    <g:img file="spash_avatar-44x45.png" alt="Avatar par defaut"/>
                                </div>
                                <h4 class="title">${ad.title}</h4>

                                <p class="intro">
                                    ${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: ad.message, strict: true).toString(), 50))}
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

                <div class="col-md-offset-2 col-md-5 col-sm-6">
                    <h3>Recherches d'equipes</h3>
                    <ul>
                        <g:each in="${usersLookingForATeam}" var="user">
                            <li class="ad">
                                <div class="avatar">
                                    <g:img file="spash_avatar-44x45.png" alt="Avatar par defaut"/>
                                </div>
                                <h4 class="title">
                                    %{-- Keep g:if at the end of the line so as not to create an undesired whitespace--}%
                                    ${user.username}, rang ${user.playerProfile.rank}<g:if
                                            test="${user.playerProfile.lookingForCompetition}">,
                                            recherche une equipe ${user.playerProfile.competitionTypes.join(' et ')}
                                    </g:if>
                                </h4>

                                <p class="intro">
                                    ${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: user.playerProfile.presentation, strict: true).toString(), 50))}
                                </p>

                                <div class="read-more">
                                <g:link mapping="profile" id="${user.id}" params="${[slug: user.slug]}">
                                en lire +
                                </g:link>
                                </div>
                            </li>
                        </g:each>
                    </ul>
                    %{--<ul>--}%
                    %{--<g:each in="${RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAM_AD, [sort: 'createdAt', order: 'desc', max: 6])}"--}%
                    %{--var="ad">--}%
                    %{--<li class="ad">--}%
                    %{--<div class="avatar">--}%
                    %{--<g:img file="spash_avatar-44x45.png" alt="Avatar par defaut" />--}%
                    %{--</div>--}%
                    %{--<h4 class="title">${ad.title}</h4>--}%

                    %{--<p class="intro">--}%
                    %{--${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: ad.message, strict: true).toString(), 50))}--}%
                    %{--</p>--}%

                    %{--<div class="read-more">--}%
                    %{--<g:link mapping="recruitment_show_ad" id="${ad.id}">--}%
                    %{--en lire +--}%
                    %{--</g:link>--}%
                    %{--</div>--}%
                    %{--</li>--}%
                    %{--</g:each>--}%
                    %{--</ul>--}%
                </div>
            </div>
        </main>
    </div>
</div>

</body>
</html>
