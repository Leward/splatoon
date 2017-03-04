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

<g:if test="${tournamentEvent.challongeUrl != null}">
    <g:panel title="Résultats">
        <g:challonge url="${tournamentEvent.challongeUrl}" />
    </g:panel>
</g:if>

<g:if test="${tournamentEvent.streamUrl != null}">
<g:panel title="Stream">
    <g:stream event="${tournamentEvent}" />
</g:panel>
</g:if>

</body>
</html>
