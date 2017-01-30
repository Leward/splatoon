<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <nav>
            <ul>
                <li><g:link class="create" action="create">Ajouter un organisateur</g:link></li>
            </ul>
        </nav>

        <div class="panel">
            <div class="panel-header">
                <h2>Liste des organizateurs de tournoi</h2>
            </div>
            <div class="panel-body">
                <f:table collection="${tournamentOrganizerList}" properties="['id', 'name', 'website']" />
            </div>
        </div>

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