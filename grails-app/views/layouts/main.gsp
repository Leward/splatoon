<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Splatoon"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<nav class="main-nav">
    <ul>
        <li>
            <g:link mapping="index">Splatoon</g:link>
        </li>
        <li>Inkipedia</li>
        <li><img src="${assetPath(src: '/splatoon-logo.svg')}" class="logo"></li>
        <li>Live Matches</li>
        <li>Ladder / Ranking</li>
    </ul>
</nav>

<g:layoutBody/>

<footer>
    <ul>
        <li><g:link mapping="dev">Dev Info</g:link></li>
        <li><g:link mapping="styleguide">Style Guide</g:link></li>
    </ul>
</footer>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
