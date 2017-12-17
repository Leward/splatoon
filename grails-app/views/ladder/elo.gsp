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
                    <th><span>Équipe</span></th>
                    <th><span>Points</span></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${rankings.ranking}" var="scoreForTeam">
                    <tr>
                        <th>${scoreForTeam.value}</th>
                        <td>${scoreForTeam.key}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>

        </main>
    </div>

</div>

</body>
</html>