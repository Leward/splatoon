<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Organisateurs de tournois (TO)</li>
    </ul>
</nav>

<g:panel title="Liste des organizateurs de tournoi">
    <p>
        <g:link class="btn btn-primary" mapping="admin_tournamentOrganizer_add">
            Ajouter un organisateur
        </g:link>
    </p>
    <f:table collection="${tournamentOrganizerList}" properties="['id', 'name', 'website']"/>
</g:panel>

%{--<div id="list-tournamentOrganizer" class="content scaffold-list" role="main">--}%
%{--<h1><g:message code="default.list.label" args="[entityName]" /></h1>--}%
%{--<g:if test="${flash.message}">--}%
%{--<div class="message" role="status">${flash.message}</div>--}%
%{--</g:if>--}%
%{--<f:table collection="${tournamentOrganizerList}" />--}%

%{--<div class="pagination">--}%
%{--<g:paginate total="${tournamentOrganizerCount ?: 0}" />--}%
%{--</div>--}%
%{--</div>--}%
</body>
</html>