<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentOrganizer.label', default: 'TournamentOrganizer')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<nav>
    <ul>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</nav>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:hasErrors bean="${this.tournamentOrganizer}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.tournamentOrganizer}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                    error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<div class="panel">
    <div class="panel-header">
        <h2>Ajouter un organisateur de tournoi</h2>
    </div>

    <div class="panel-body padded">
        <g:form action="save">
            <fieldset class="form">
                <f:all bean="tournamentOrganizer"/>
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>
