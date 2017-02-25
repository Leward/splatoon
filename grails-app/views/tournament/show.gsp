<%@ page import="splatoon.DateConversions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournament.label', default: 'Tournament')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_tournament_list">Tournois</g:link></li>
        <li>${tournament.name}</li>
    </ul>
</nav>

<g:panel title="Détail du tournoi: ${tournament.name}">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <f:display bean="tournament"/>

    <g:form resource="${this.tournament}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit btn btn-primary" action="edit" resource="${this.tournament}"><g:message
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
