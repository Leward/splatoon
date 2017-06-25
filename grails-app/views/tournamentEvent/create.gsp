<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'tournamentEvent.label', default: 'TournamentEvent')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<nav>
    <ul>
        <li><g:link mapping="admin">Retour administration</g:link></li>
        <li><g:link controller="tournamentEvent" action="index">Liste des événements</g:link></li>
    </ul>
</nav>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

%{--<g:panel title="Créer un événement pour un tournoi">--}%

    <g:hasErrors bean="${this.tournamentEvent}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.tournamentEvent}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save">
        <fieldset class="form">
            %{-- There is an error when using f:all --}%
            %{--<f:all bean="tournamentEvent" />--}%
            <f:field property="tournament" bean="tournamentEvent" />
            <f:field property="date" bean="tournamentEvent" />
            <f:field property="startTime" bean="tournamentEvent" />
            <f:field property="endTime" bean="tournamentEvent" />
            <f:field property="challongeUrl" bean="tournamentEvent" />
            <f:field property="streamUrl" bean="tournamentEvent" />
        </fieldset>

        <g:include view="tournamentEvent/_info_url_formats.gsp" />

        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
%{--</g:panel>--}%
</body>
</html>
