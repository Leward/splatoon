<%@ page import="splatoon.TournamentEvent" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Ink Zone France"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<header>
    <nav>
        <h1 class="logo">
            <g:link mapping="index">Ink Zone</g:link>
        </h1>
        <ul class="links-1">
            <li class="separator"></li>
            <li>
                <g:link url="http://fr.splatoonwiki.org/wiki/Accueil">Stream TV</g:link>
            </li>
            <li class="separator"></li>
            <li><g:link mapping="recruitment">Le mag</g:link></li>
            <li class="separator"></li>
            <li><g:link mapping="ladder">Ladder</g:link></li>
            <li class="separator"></li>
            <li><g:link mapping="recruitment">Recrutements</g:link></li>
        </ul>
        <ul class="links-2">
            <li>
                <sec:ifLoggedIn>
                    <g:link controller="logout" action="index">Déconnexion</g:link>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <g:link mapping="login">Connexion</g:link>
                </sec:ifNotLoggedIn>
            </li>
            <li>

            </li>
        </ul>
    </nav>
</header>

<div class="flex-container">
    <nav class="next-events">
        <g:render template="/tournamentEvent/nextEvents"
                  model="${[nextEvents: TournamentEvent.findUpcomingEvents(6)]}"/>
    </nav>
    <main>
        <div class="container-fluid">
            <g:layoutBody/>
        </div>
    </main>
</div>

%{--<footer>--}%
    %{--<ul>--}%
        %{--<li><g:link mapping="admin">Administration</g:link></li>--}%
        %{--<li><g:link mapping="dev">Dev Info</g:link></li>--}%
        %{--<li><g:link mapping="styleguide">Style Guide</g:link></li>--}%
    %{--</ul>--}%
%{--</footer>--}%

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>
<script src="/static/ckeditor/ckeditor.js"></script>

</body>
</html>
