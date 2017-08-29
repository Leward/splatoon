<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Evenements</li>
    </ul>
</nav>

<g:panel title="Liste des évenements programmés">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <p>
        <g:link class="btn btn-primary" action="create">
            Ajouter un evenement
        </g:link>
    </p>

    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>Heure</th>
            <th>Tournoi</th>
            <th>Ladders</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${tournamentEventList}" var="tournamentEvent" status="i">
            <tr>
                <td>
                    <g:link action="show" id="${tournamentEvent.id}">
                        ${tournamentEvent.date}
                    </g:link>
                </td>
                <td>
                    <abbr title="Debut">${tournamentEvent.startTime}</abbr><br>
                    <abbr title="Fin">${tournamentEvent.endTime}</abbr>
                </td>
                <td>
                    <g:link mapping="admin_tournament_show" id="${tournamentEvent.tournament.id}">
                        ${tournamentEvent.tournament.name}
                    </g:link>
                </td>
                <td>${tournamentEvent.ladderEntries.size()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

</g:panel>


<div class="pagination">
    <g:paginate total="${tournamentEventCount}" max="${params.max}" />
</div>
</body>
</html>