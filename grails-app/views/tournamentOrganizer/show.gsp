<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_tournamentOrganizer_list">Organisateurs de tournois (TO)</g:link></li>
        <li>${tournamentOrganizer.name}</li>
    </ul>
</nav>
<g:panel title="Détail pour ${tournamentOrganizer.name}">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="tournamentOrganizer"/>

    <g:form resource="${this.tournamentOrganizer}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit btn btn-primary" action="edit" resource="${this.tournamentOrganizer}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <button type="submit" class="delete btn btn-danger" type="submit"
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                Supprimer
            </button>
        </fieldset>
    </g:form>
</g:panel>
</body>
</html>
