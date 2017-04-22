<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inscription</title>
</head>

<body>

<div class="registration-page">

    <g:panel title="Pas encore de profil ?" class="register">
        <g:include view="user/_register_introduction.gsp"/>
        <g:include view="user/_register_form.gsp" model="${[newUser: newUser]}"/>
    </g:panel>

</div>

</body>
</html>
