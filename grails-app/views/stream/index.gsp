<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Streams</title>
</head>

<body>

<div class="streams card">
    <header><h2>Lecteur</h2></header>
    <main>
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
    </main>
</div>

<div class="streams card">
    <header><h2>Streams</h2></header>
    <main>
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
    </main>
</div>

</body>
</html>