<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mon Espace</title>
</head>

<body>

<g:panel title="Mon espace">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <p>Bienvenue, ${me.username} !</p>

    <ul>
        <li><g:link mapping="change_password">Changer de mot de passe</g:link></li>
        <li><g:link mapping="update_information">Modifier mes informations</g:link></li>
    </ul>
</g:panel>

<g:panel title="Mes informations">
    <dl>
        <dt>Pseudo :</dt>
        <dd>${me.username}</dd>
        <dt>Identifiant Nintendo :</dt>
        <dd>${me.nintendoId ?: 'Inconnu'}</dd>
    </dl>
</g:panel>

<g:if test="${me.teams}">
    <g:panel>
        <h2>Mes equipes</h2>
        <ul>
            <g:each in="${me.teams}" var="team">
                <li>${team}</li>
            </g:each>
        </ul>
    </g:panel>
</g:if>


</body>
</html>
