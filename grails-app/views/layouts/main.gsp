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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    %{-- /static directory comes from Spring Boot handling of src/main/resources/public --}%
    <link rel="stylesheet" href="/static/flag-icon-css/css/flag-icon.min.css">
    <asset:link rel="icon" href="favicon.png" type="image/png"/>

    <g:layoutHead/>
</head>

<body>

<header>
    <nav>
        <h1 class="logo">
            <g:link mapping="index">
                <g:img file="logo-wihite-horizontal-small.png" alt="Ink Zone"/>
            </g:link>
        </h1>
        <ul class="links-1">
            <li class="separator"></li>
            <li>
                <g:link mapping="streams">
                    <g:img file="icon_stream.png" alt="Icone Stream TV" />
                    Stream TV
                </g:link>
            </li>
            <li class="separator"></li>
            <li><g:link mapping="magazine">
                <g:img file="icon_mag.png" alt="Icone Le Mag" />
                <span class="small-menu">Mag</span>
                <span class="large-menu">Le Mag</span>
            </g:link></li>
            <li class="separator"></li>
            <li>
                <g:link mapping="ladder">
                    <g:img file="icon_ladder.png" alt="Icone ladder" />
                    Ladder
                </g:link>
            </li>
            <li class="separator"></li>
            <li>
                <g:link mapping="recruitment">
                    <g:img file="icon_recruitment.png" alt="Icone recrutement" />
                    Recrutements
                </g:link>
            </li>
        </ul>
        <ul class="links-2">
            <sec:ifLoggedIn>
                <li>
                    <g:link mapping="my_account">
                        <g:img file="icon_profile.png" alt="Icone profil" />
                        Mon espace
                    </g:link>
                </li>
                <li><g:link controller="logout" action="index">Déconnexion</g:link></li>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <li><g:link mapping="login">Connexion</g:link></li>
            </sec:ifNotLoggedIn>
            <li class="separator"></li>
            <li>
                <a href="https://twitter.com/ink_zone" class="icon">
                    <img src="${resource(dir: 'images', file: 'twitter.png')}" alt="Twitter" class="icon">
                </a>
                <a href="https://discord.gg/PxpqxVn" class="icon">
                    <img src="${resource(dir: 'images', file: 'discord.png')}" alt="Discord" class="icon">
                </a>
            </li>
        </ul>
    </nav>
</header>

<div class="flex-container">
    <nav class="next-events">
        <g:render template="/tournamentEvent/nextEvents"
                  model="${[nextEvents: TournamentEvent.findUpcomingEvents(10)]}"/>
    </nav>
    <main>
        <div class="container-fluid">
            <g:layoutBody/>
        </div>

        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_TO,ROLE_EDITOR">
            <p class="text-center"><g:link mapping="admin">Administration</g:link></p>
        </sec:ifAnyGranted>

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

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script async="" charset="utf-8" src="//platform.twitter.com/widgets.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/webshim/1.16.0/dev/polyfiller.js"></script>

%{-- Load CKEditor only on pages where it is required --}%
<!-- Use CKEditor: ${useCkEditor == true}-->
<g:if test="${useCkEditor}">
    <script src="/static/ckeditor/ckeditor.js"></script>
</g:if>
%{-- Load ChartJS only on pages where it is required --}%
<!-- Use ChartJS: ${useChartJS == true}-->
<g:if test="${useChartJS}">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.min.js"></script>
</g:if>

<asset:javascript src="application.js"/>

</body>
</html>
