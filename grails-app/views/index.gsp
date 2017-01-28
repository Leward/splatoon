<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

<g:render template="tournamentOrganizer/shortList" model="${[tournamentOrganizers: TournamentOrganizer.findAll()]}" />

<g:render template="tournamentEvent/nextEvents" model="${[nextEvents: TournamentEvent.findUpcomingEvents()]}" />
%{--<g:include controller="tournamentEvent" action="showNextEvents" />--}%

</body>
</html>
