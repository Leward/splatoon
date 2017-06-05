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
                    <th>Évolution</th>
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
                            <td>${rankedTeam.evolution}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </main>
    </div>

</div>

</body>
</html>