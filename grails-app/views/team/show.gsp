<%@ page import="splatoon.DateConversions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Equipe ${team.name}</title>
</head>

<body>

<g:set var="rankingName" value="${tournamentOrganizer ? tournamentOrganizer.name : 'Général'}"/>

<div class="team-page">

    <g:panel title="${team.name} - Classement ${rankingName}">
        <div class="rankings">
            <div class="ranking-container">
                <div class="ranking">
                    <div class="rank">${ranking.rank}</div>

                    <div class="rank-label">
                        <span class="type">
                            ${rankingName}
                        </span>
                        <span class="description">Ranking</span>
                    </div>
                </div>
            </div>
        </div>

        <p>
            <b>Type d'equipe :</b> <g:message code="${team.type.i18nMessage}"/>
        </p>

        <p>
            <b>Nationalite:</b>
            <span class="flag-icon flag-icon-${team.countryCode}"></span>
            <g:message code="team.nationality.${team.countryCode}"/>
        </p>

        <g:if test="${team.members.size() > 0}">
            <b>Joueurs:</b>

            <div class="players">
                <g:each in="${team.members}" var="player">
                    <div class="player">
                        <div class="avatar">
                            <g:if test="${player.avatar}">
                                <img src="${player.avatar}" alt="Avatar"/>
                            </g:if>
                            <g:else>
                                <asset:image src="default_player_avatar.png" alt="Default avatar"/>
                            </g:else>
                        </div>

                        <div class="details">
                            <div class="name">${player.name}</div>

                            <div class="roles">
                                <g:each in="${player.roles}" var="role" status="i">
                                    <g:message code="${role.i18nMessage}"/><g:if
                                        test="${i < player.roles.size() - 1}">,</g:if>
                                </g:each>
                            </div>
                        </div>
                    </div>
                </g:each>
            </div>

        </g:if>
    </g:panel>

    <g:panel title="Classements">
        <table>
            <thead>
            <tr>
                <th><span>Tournois</span></th>
                <th><span>Wins</span></th>
                <th><span>Loses</span></th>
                <th><span>Ratio</span></th>
                <th><span>Évolution</span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${ranking.nbTournaments}</td>
                <td>${ranking.wins}</td>
                <td>${ranking.loses}</td>
                <td>${ranking.ratioAsString}</td>
                <td><g:evolution rankedTeam="${ranking}"/></td>
            </tr>
            </tbody>
        </table>
    </g:panel>

    <g:panel title="Performances">
        <div class="chart-container" style="position: relative; width: 100%; max-height: 400px;">
            <canvas id="team-performances-canvas"></canvas>
        </div>

        <script>
            window.teamChartData = ${raw(ranking.performances.chartDataAsJson)};
        </script>
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
                <g:if test="${tournamentOrganizer == null || ladder.event.tournament.organizer == tournamentOrganizer}">
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
                </g:if>
            </g:each>
            </tbody>
        </table>
    </g:panel>

</div>

</body>
</html>