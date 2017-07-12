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

    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>

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

    <sec:ifAnyGranted roles="ROLE_ADMIN">
        <g:panel title="Remise à zéro">
            <p>Les administrateurs peuvent remettre le Ladder à zéro. Attention, cette opération est irréversible !</p>

            <p>
                <g:form mapping="admin_ladder_reset" method="POST">
                    <button type="submit" class="btn btn-danger"
                            onclick="return confirm('Êtes-vous sûr de vouloir remettre la ladder à zéro ? Cette action est irréversible. ');">
                        Remettre le ladder à zéro
                    </button>
                </g:form>
            </p>
        </g:panel>
    </sec:ifAnyGranted>

</div>

</body>
</html>