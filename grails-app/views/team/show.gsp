<%@ page import="splatoon.DateConversions" %>
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
            <b>Type d'equipe :</b> <g:message code="${team.type.i18nMessage}"/>
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
                <g:set var="rankedTeam" value="${toRankings.getRankedTeam(team)}"/>
                <g:if test="${rankedTeam.rank != null && rankedTeam.rank > 0}">
                    <tr>
                        <td>${to.name}</td>
                        <td>${rankedTeam.rank}</td>
                        <td>${rankedTeam.nbTournaments}</td>
                        <td>${rankedTeam.wins}</td>
                        <td><g:evolution rankedTeam="${rankedTeam}"/></td>
                    </tr>
                </g:if>
            </g:each>
            </tbody>
        </table>
    </g:panel>

    <g:panel title="Resultats">
        <table>
            <thead>
            <tr>
                <th>Date</th>
                <th>Tournoi</th>
                <th>Wins</th>
                <th>Loses</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${results}" var="ladder">
                <tr>
                    <td>
                        <g:link mapping="tournament_event" id="${ladder.event.id}">
                            <g:formatDate format="dd MMM yyyy" date="${DateConversions.asDate(ladder.event.date)}"
                                          locale="fr"/>
                        </g:link>
                    </td>
                    <td>
                        <g:link mapping="tournament_event" id="${ladder.event.id}">
                            ${ladder.event.tournament.name}
                        </g:link>
                    </td>
                    <td>${ladder.wins}</td>
                    <td>${ladder.loses}</td>
                    <td>${ladder.points}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:panel>

</div>

</body>
</html>