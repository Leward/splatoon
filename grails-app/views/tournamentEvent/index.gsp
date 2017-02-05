<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<nav>
    <ul>
        <li><g:link mapping="admin">Retour administration</g:link></li>
        <li><g:link class="create" action="create">Nouvel événement</g:link></li>
    </ul>
</nav>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<g:panel title="Liste des évenements programmés">
    <f:table collection="${tournamentEventList}" />
</g:panel>



%{--<div class="pagination">--}%
%{--<g:paginate total="${tournamentEventCount ?: 0}" />--}%
</div>
</body>
</html>