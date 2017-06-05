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
            <li>Gestion du Ladder</li>
        </ul>
    </nav>

    <div class="card">
        <header><h2>Gestion du Ladder</h2></header>
        <main>
            <table>
                <thead>
                <tr>
                    <th>Tournoi</th>
                    <th>Date</th>
                    <th>TO</th>
                    <th>Résultats enregistrés</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${splatoon.TournamentEvent.findAll()}" var="event">
                    <tr>
                        <th>
                            <g:link mapping="admin_ladder_event_details" id="${event.id}">
                                ${event.tournament.name}
                            </g:link>
                        </th>
                        <td>${event.formattedDate}</td>
                        <td>${event.tournament.organizer}</td>
                        <td>
                            <g:link mapping="admin_ladder_event_details" id="${event.id}">
                                ${event.ladderEntries.size()}
                            </g:link>
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