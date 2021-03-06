<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link action="index">Evenements</g:link></li>
        <li>Modifier</li>
    </ul>
</nav>

<g:panel title="Modifier un evenement">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${this.tournamentEvent}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.tournamentEvent}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form resource="${this.tournamentEvent}" method="PUT">
        <g:hiddenField name="version" value="${this.tournamentEvent?.version}"/>
        <fieldset class="form">
            <f:all bean="tournamentEvent"/>
        </fieldset>
        <g:include view="tournamentEvent/_info_url_formats.gsp"/>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>
