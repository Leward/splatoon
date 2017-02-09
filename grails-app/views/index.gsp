<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Portail Splatoon France</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<div class="home-page">
    <div class="next-events">
        <g:render template="tournamentEvent/nextEvents" model="${[nextEvents: TournamentEvent.findUpcomingEvents(6)]}"/>
    </div>

    <div class="news">
        <g:panel title="Les dernières nouvelles">
            <p>Aucun contenu pour le moment.</p>
        </g:panel>
    </div>
</div>

</body>
</html>
