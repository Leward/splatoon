<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion du Ladder</title>
</head>

<body>

<div class="ladder-page">

    <nav>
        <ul class="breadcrumb">
            <li><g:link mapping="admin">Administration</g:link></li>
            <li><g:link mapping="admin_ladder">Gestion du Ladder</g:link></li>
            <li>${event.tournament}</li>
        </ul>
    </nav>

    <div class="card">
        <header>
            <h2>
                Gestion du Ladder pour ${event.tournament} <br>
                <small>${event.getFormattedDate()}</small>
            </h2>
        </header>
        <main>
            <div class="buttons">
                <g:link mapping="admin_ladder_add_result" params="${[eventId: event.id]}" class="btn btn-primary">
                    Ajouter un résultat
                </g:link>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Equipe</th>
                    <th>Wins</th>
                    <th>Loses</th>
                    <th>Points</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${event.ladderEntries}" var="ladder">
                    <tr>
                        <th>
                            <g:link mapping="admin_ladder_update_result" id="${ladder.id}">
                            ${ladder.team.name}
                            </g:link>
                        </th>
                        <td>${ladder.wins}</td>
                        <td>${ladder.loses}</td>
                        <td>${ladder.points}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </main>
    </div>

</div>

</body>
</html>