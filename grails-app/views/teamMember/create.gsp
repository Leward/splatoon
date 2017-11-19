<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ajouter un membre d'équipe</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link controller="team" action="index">Gestion des équipes</g:link></li>
        <li><g:link controller="team" action="showAdmin" id="${teamMember.team.id}">${teamMember.team.name}</g:link></li>
        <li>Ajouter un membre d'équipe</li>
    </ul>
</nav>

<g:panel title="Ajouter un membre d'équipe">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${this.teamMember}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.teamMember}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="create" id="${teamMember.id}" method="POST">
        <g:hiddenField name="version" value="${this.team?.version}"/>
        <fieldset class="form">
            <f:all bean="teamMember" />
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>
