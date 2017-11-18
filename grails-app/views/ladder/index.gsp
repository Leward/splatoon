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
                    <th><span>Matchs</span></th>
                    <th><span>Ratio</span></th>
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
                            <td>
                                <span class="flag-icon flag-icon-${rankedTeam.team.countryCode}"></span>
                                <g:link mapping="team_show" id="${rankedTeam.team.id}">
                                    ${rankedTeam.team.name}
                                </g:link>
                            </td>
                            <td>${rankedTeam.nbTournaments}</td>
                            <td>${rankedTeam.nbGames}</td>
                            <td>${rankedTeam.ratioAsString}</td>
                            <td>${rankedTeam.points}</td>
                            <td class="evolution">
                                <g:evolution rankedTeam="${rankedTeam}" />
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