<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournament.label', default: 'Tournament')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_tournament_list">Tournois</g:link></li>
        <li>Ajouter</li>
    </ul>
</nav>

<g:panel title="Ajouter un Tournoi">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${this.tournament}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.tournament}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="save">
        <fieldset class="form">
            <f:all bean="tournament" except="events"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create btn btn-primary" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</g:panel>
</body>
</html>
