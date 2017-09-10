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
        <main class="row">
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
                    <g:link mapping="streams">Voir plus de streams</g:link>
                </p>
            </div>
        </main>
    </div>

    <div class="featured-news card">
        <header class="red"><h2>
            <g:link mapping="news">News</g:link>
        </h2></header>
        <main><g:render template="/news/latest_news"/></main>
    </div>

    <div class="featured-news card">
        <header class="red"><h2>
            <g:link mapping="magazine">Magazine</g:link>
        </h2></header>
        <main><g:render template="/article/latest_articles"/></main>
    </div>

    <div class="recruitment card">
        <header class="blue"><h2>
            <g:link mapping="recruitment">
                Recrutements & Candidatures
            </g:link>
        </h2></header>

        <main class="row">
            <div class="col-md-5 col-sm-6">
                <h3>Recherches de joueurs</h3>
                <ul class="ads">
                    <g:each in="${RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAMMATE_AD, [max: 6, sort: "createdAt", order: "desc"])}" var="ad">
                        <li class="ad">
                            <h4 class="title">${ad.title}</h4>

                            ${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: ad.message, strict: true).toString(), 50))}

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
                    <g:each in="${RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAM_AD, [sort: 'createdAt', order: 'desc', max: 6])}" var="ad">
                        <li class="ad">
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
        </main>
    </div>
</div>

</body>
</html>
