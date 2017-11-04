<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Equipe ${team.name}</title>
</head>

<body>

<div class="team-page">

    <g:panel title="${team.name}">
        <p>
            <b>Type d'equipe :</b> <g:message code="${team.type.i18nMessage}" />
        </p>
    </g:panel>

    <g:panel title="Classements">
        <table>
            <thead>
            <tr>
                <th>Classement</th>
                <th>Rang</th>
                <th>Tournois</th>
                <th>Wins</th>
                <th>Évolution</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Général</td>
                <td>${rankings.globalRankings.getRankedTeam(team).rank}</td>
                <td>${rankings.globalRankings.getRankedTeam(team).nbTournaments}</td>
                <td>${rankings.globalRankings.getRankedTeam(team).wins}</td>
                <td><g:evolution rankedTeam="${rankings.globalRankings.getRankedTeam(team)}"/></td>
            </tr>
            <g:each in="${rankings.listTournamentOrganizers()}" var="to">
                <g:set var="toRankings" value="${rankings.perTournamentOrganizerRankings.get(to)}"/>
                <tr>
                    <td>${to.name}</td>
                    <td>${toRankings.getRankedTeam(team).rank}</td>
                    <td>${toRankings.getRankedTeam(team).nbTournaments}</td>
                    <td>${toRankings.getRankedTeam(team).wins}</td>
                    <td><g:evolution rankedTeam="${toRankings.getRankedTeam(team)}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:panel>

</div>

</body>
</html>