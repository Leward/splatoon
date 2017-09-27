<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ladder</title>
</head>

<body>

<div class="ladder-page">

    <div class="card">
        <header><h2>Ladder</h2></header>
        <main>
            <h4>Classement</h4>
            <p>
            <div>
                <g:link class="btn ${selectedOrganizer == null ? 'btn-primary' : 'btn-default'}">Général</g:link>
                <g:each in="${tournamentOrganizers}" var="${tournamentOrganizer}">
                    <g:link mapping="ladder" params="${[id: tournamentOrganizer.id]}" class="btn  ${selectedOrganizer == tournamentOrganizer ? 'btn-primary' : 'btn-default'}">${tournamentOrganizer}</g:link>
                </g:each>
            </div>
            </p>
            <table>
                <thead>
                <tr>
                    <th><span>Rang</span></th>
                    <th><span>Équipe</span></th>
                    <th><span>Tournois</span></th>
                    <th><span>Wins</span></th>
                    <th><span>Loses</span></th>
                    <th><span>Points</span></th>
                    <th class="evolution">
                        <abbr title="Evolution du rang sur les 7 derniers jours">
                            Évolution
                        </abbr>
                    </th>
                </tr>
                </thead>
                <tbody>
                    <g:each in="${rankedTeams.list}" var="rankedTeam">
                        <tr>
                            <th>${rankedTeam.rank}</th>
                            <td>${rankedTeam.team.name}</td>
                            <td>${rankedTeam.nbTournaments}</td>
                            <td>${rankedTeam.wins}</td>
                            <td>${rankedTeam.loses}</td>
                            <td>${rankedTeam.points}</td>
                            <td class="evolution">
                                <g:if test="${rankedTeam.evolution == splatoon.RankedTeam.Evolution.BETTER}">
                                    <g:img src="up-small.png" class="evolution" title="Rang il y a 7 jours: ${rankedTeam.previousRank}" />
                                    <span class="hidden-xs">(+ ${Math.abs(rankedTeam.previousRank - rankedTeam.rank)} places)</span>
                                </g:if>
                                <g:if test="${rankedTeam.evolution == splatoon.RankedTeam.Evolution.WORSE}">
                                    <g:img src="down-small.png" class="evolution" title="Rang il y a 7 jours: ${rankedTeam.previousRank}"/>
                                    <span class="hidden-xs">(- ${Math.abs(rankedTeam.previousRank - rankedTeam.rank)} places)</span>
                                </g:if>
                                <g:if test="${rankedTeam.evolution == splatoon.RankedTeam.Evolution.SAME}">
                                    =
                                </g:if>
                                <g:if test="${rankedTeam.evolution == splatoon.RankedTeam.Evolution.NEW}">
                                    <span class="hidden-xs visible-md">Nouveau</span>
                                    <span class="hidden-xs hidden-md">New</span>
                                </g:if>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </main>
    </div>

</div>

</body>
</html>