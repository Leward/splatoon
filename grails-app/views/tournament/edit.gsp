<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournament.label', default: 'Tournament')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_tournament_list">Tournois</g:link></li>
        <li><g:link mapping="admin_tournament_show" id="${tournament.id}">${tournament.name}</g:link></li>
        <li>Modifier</li>
    </ul>
</nav>

<g:panel title="Modifier tournoi">
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

    <g:form resource="${this.tournament}" method="PUT">
        <g:hiddenField name="version" value="${this.tournament?.version}"/>
        <fieldset class="form">
            <f:all bean="tournament"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save btn btn-primary" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>

</g:panel>

</body>
</html>
