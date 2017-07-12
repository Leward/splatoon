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
            <table>
                <thead>
                <tr>
                    <th>Rang</th>
                    <th>Équipe</th>
                    <th>Wins</th>
                    <th>Loses</th>
                    <th>Points</th>
                    <th class="evolution">Évolution</th>
                </tr>
                </thead>
                <tbody>
                    <g:each in="${rankedTeams.list}" var="rankedTeam">
                        <tr>
                            <th>${rankedTeam.rank}</th>
                            <td>${rankedTeam.team.name}</td>
                            <td>${rankedTeam.wins}</td>
                            <td>${rankedTeam.loses}</td>
                            <td>${rankedTeam.points}</td>
                            <td class="evolution">
                                <g:if test="${rankedTeam.evolution == splatoon.RankedTeam.Evolution.BETTER}">
                                    <g:img src="up-small.png" class="evolution" />
                                </g:if>
                                <g:if test="${rankedTeam.evolution == splatoon.RankedTeam.Evolution.WORSE}">
                                    <g:img src="down-small.png" class="evolution" />
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