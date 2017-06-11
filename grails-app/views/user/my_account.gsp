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

    <ul>
        <li><g:link mapping="change_password">Changer de mot de passe</g:link></li>
    </ul>
</g:panel>

</body>
</html>
