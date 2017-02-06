<%@ page import="java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

%{--<nav>--}%
    %{--<ul>--}%
        %{--<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>--}%
    %{--</ul>--}%
%{--</nav>--}%

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

<g:panel title="Gérer">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form resource="${this.tournamentEvent}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.tournamentEvent}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</g:panel>
</body>
</html>
