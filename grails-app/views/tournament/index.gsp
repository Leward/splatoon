<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournament.label', default: 'Tournament')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Tournois</li>
    </ul>
</nav>

<g:panel title="Liste des Tournois">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <p>
        <g:link class="btn btn-primary" mapping="admin_tournament_add">
            Ajouter un tournoi
        </g:link>
    </p>

    <f:table collection="${tournamentList}" properties="['name', 'organizer']"/>

</g:panel>
</body>
</html>