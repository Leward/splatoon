<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ladder (ELO)</title>
</head>

<body>

<div class="ladder-page">

    <div class="card">
        <header><h2>Ladder (ELO)</h2></header>
        <main>
            <h4>Classement</h4>
            <p>
            <div>
                <g:link mapping="ladder_elo" class="btn ${selectedOrganizer == null ? 'btn-primary' : 'btn-default'}">Général</g:link>
                <g:each in="${tournamentOrganizers}" var="${tournamentOrganizer}">
                    <g:link mapping="ladder_elo" params="${[id: tournamentOrganizer.id]}" class="btn  ${selectedOrganizer == tournamentOrganizer ? 'btn-primary' : 'btn-default'}">${tournamentOrganizer}</g:link>
                </g:each>
            </div>
        </p>
            <table>
                <thead>
                <tr>
                    <th>Rang</th>
                    <th><span>Équipe</span></th>
                    <th><span>Points</span></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${rankings.ranking}" var="teamEloRanking" status="i">
                    <tr>
                        <th>${i + 1}</th>
                        <th>${teamEloRanking.team}</th>
                        <td>${teamEloRanking.elo}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>

            <hr>

            <div class="alert alert-info">
                Le classement utilise un mode de calcul appele "ELO".
                Il s'agit d'un classement qui prend en compte le niveau relatif des equipe s'affrontant,
                encourageant les equipes d'un niveau similaire a s'affronter et donne l'opportunite aux petites
                equipe d'avancer dans le classement en battant de plus grosses equipes.
            </div>

        </main>
    </div>

</div>

</body>
</html>