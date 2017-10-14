<%@ page import="java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<g:if test="${flash.message}">
    <div class="alert alert-info" role="status">${flash.message}</div>
</g:if>

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

<g:panel title="Participants">
    <table class="table">
        <thead>
        <tr>
            <th>Equipe</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${tournamentEvent.registrations}" var="registration">
            <tr>
                <td>${registration.team.name}</td>
                <td>
                    <g:form controller="tournamentRegistration" action="delete" method="DELETE" id="${registration.id}">
                        <button type="submit" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir retirer cette equipe du tournoi ?');">
                            Retirer
                        </button>
                    </g:form>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <p>
        <g:link mapping="admin_tournamentParticipation_add" params="${['event.id': tournamentEvent.id]}"
                class="btn btn-primary">
            Ajouter une equipe au tournoi
        </g:link>
    </p>
</g:panel>

<g:panel title="Gérer">
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
