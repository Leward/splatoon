<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ajouter une equipe a un tournoi</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link controller="tournamentEvent" action="index">Evenements</g:link></li>
        <g:if test="${tournamentRegistration.event}">
            <li>
                <g:link mapping="admin_tournamentEvent_show" id="${tournamentRegistration.event.id}">
                    ${tournamentRegistration.event}
                </g:link>
            </li>
        </g:if>
        <li>Ajouter une equipe participante</li>
    </ul>
</nav>


<g:panel title="Ajouter equipe au tournoi">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.tournamentRegistration}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.tournamentRegistration}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form mapping="admin_tournamentParticipation_add" method="POST">
        <fieldset class="form">
            <f:with bean="tournamentRegistration">
                <f:field property="event" />
                <f:field property="team" />
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>
