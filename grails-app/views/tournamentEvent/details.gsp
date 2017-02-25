<%@ page import="java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<g:panel
        title="${tournamentEvent.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE))} : ${tournamentEvent.tournament}"
        padded="true">
    <p>
        De ${tournamentEvent.startTime} à ${tournamentEvent.endTime}
    </p>
    <p>
        Organisateur:
        <g:link controller="tournamentOrganizer" action="show" id="${tournamentEvent.tournament.organizer.id}">
            ${tournamentEvent.tournament.organizer}
        </g:link>
    </p>
</g:panel>

<g:panel title="Résultats">
    <iframe src="http://clubcashalot.challonge.com/cashalotcupcoupedefrance/module"
            width="100%"
            height="500"
            frameborder="0"
            scrolling="auto"
            allowtransparency="true">
    </iframe>
</g:panel>

<g:panel title="Stream">
    <iframe
            width="1084"
            height="610"
            src="https://gaming.youtube.com/embed/nAgKdmBcpCs"
            frameborder="0" allowfullscreen>
    </iframe>
</g:panel>

</body>
</html>
