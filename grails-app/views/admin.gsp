<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Admin Splatoon France</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<g:panel title="Tournois">
    <ul>
        <li><g:link controller="tournamentOrganizer" action="index">Organisateurs de tournois</g:link></li>
        <li><g:link controller="tournament" action="index">Liste des tournois</g:link></li>
        <li><g:link controller="tournamentEvent" action="index">Liste des événements</g:link></li>
    </ul>
</g:panel>

<g:panel title="Ladder">
    <ul>
        <li>Points ajoutés</li>
    </ul>
</g:panel>

<g:panel title="Utilisateurs">
    <ul>
        <li><g:link controller="user" action="index">Utilisateurs</g:link></li>
        <li>Groupes</li>
        <li>Rôles</li>
    </ul>
</g:panel>

<g:panel title="News">
    <ul>
        <li><g:link mapping="admin_news">News</g:link></li>
    </ul>
</g:panel>

</body>
</html>
