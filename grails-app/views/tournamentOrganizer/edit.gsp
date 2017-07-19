<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>

<nav class="nav" role="navigation">
    <ul>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</nav>

<div class="panel">
    <div class="panel-header">
        <h2>Modifier organisateur de tournoi</h2>
    </div>

    <div class="panel-body padded">
        <g:form action="update" method="PUT">
            <fieldset class="form">
                <g:hiddenField name="id" value="${tournamentOrganizer.id}"/>
                <f:all bean="tournamentOrganizer"/>
            </fieldset>
            <fieldset class="buttons">
                <input class="save" type="submit"
                       value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>
