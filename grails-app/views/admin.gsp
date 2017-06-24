<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Admin Splatoon France</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_TO">
    <g:panel title="Tournois">
        <ul>
            <li><g:link controller="tournamentOrganizer" action="index">Organisateurs de tournois</g:link></li>
            <li><g:link controller="tournament" action="index">Liste des tournois</g:link></li>
            <li><g:link controller="tournamentEvent" action="index">Liste des événements</g:link></li>
        </ul>
    </g:panel>

    <g:panel title="Ladder">
        <ul>
            <li><g:link mapping="admin_ladder">Gestion du Ladder</g:link></li>
            <li><g:link mapping="admin_team">Équipes</g:link></li>
        </ul>
    </g:panel>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN">
    <g:panel title="Utilisateurs">
        <ul>
            <li><g:link controller="user" action="index">Utilisateurs</g:link></li>
            <li>Groupes</li>
            <li>Rôles</li>
        </ul>
    </g:panel>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_EDITOR">
    <g:panel title=" News & Mag">
        <ul>
            <li><g:link mapping="admin_news">News</g:link></li>
            <li><g:link mapping="admin_article">Articles</g:link></li>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
                <li><g:link controller="articleCategory">Catégories d'Article</g:link></li>
            </sec:ifAnyGranted>
            <li><g:link mapping="admin_cover_list">Covers</g:link></li>
        </ul>
    </g:panel>
</sec:ifAnyGranted>

</body>
</html>
