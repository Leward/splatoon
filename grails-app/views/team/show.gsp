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
    <div class="rankings">
        <div class="ranking-container">
            <div class="ranking">
                <div class="rank">${rankings.globalRankings.getRankedTeam(team).rank}</div>

                <div class="rank-label">
                    <span class="type">General</span>
                    <span class="description">Ranking</span>
                </div>
            </div>
        </div>
    </div>

    <p>
        <b>Type d'equipe :</b> <g:message code="${team.type.i18nMessage}"/>
    </p>
</g:panel>

<g:panel title="Classements">
    <table>
        <thead>
        <tr>
            <th><span>Classement</span></th>
            <th><span>Rang</span></th>
            <th><span>Tournois</span></th>
            <th><span>Wins</span></th>
            <th><span>Loses</span></th>
            <th><span>Ratio</span></th>
            <th><span>Évolution</span></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Général</td>
            <td>${rankings.globalRankings.getRankedTeam(team).rank}</td>
            <td>${rankings.globalRankings.getRankedTeam(team).nbTournaments}</td>
            <td>${rankings.globalRankings.getRankedTeam(team).wins}</td>
            <td>${rankings.globalRankings.getRankedTeam(team).loses}</td>
            <td>${rankings.globalRankings.getRankedTeam(team).ratioAsString}</td>
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
                    <td>${rankedTeam.loses}</td>
                    <td>${rankedTeam.ratioAsString}</td>
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
            <th><span>Date</span></th>
            <th><span>Tournoi</span></th>
            <th><span>Wins</span></th>
            <th><span>Loses</span></th>
            <th><span>Points</span></th>
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