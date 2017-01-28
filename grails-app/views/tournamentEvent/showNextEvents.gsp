<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>

<div class="panel next-events">
    <g:render template="nextEvents" model="${[nextEvents: nextEvents]}"/>
</div>

</body>
</html>