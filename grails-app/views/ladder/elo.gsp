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
            <table>
                <thead>
                <tr>
                    <th>Rang</th>
                    <th><span>Équipe</span></th>
                    <th><span>Points</span></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${rankings.ranking}" var="scoreForTeam" status="i">
                    <tr>
                        <th>${i + 1}</th>
                        <th>${scoreForTeam.value}</th>
                        <td>${scoreForTeam.key}</td>
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