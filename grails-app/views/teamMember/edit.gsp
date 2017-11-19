<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Modifier un membre d'equipe</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link controller="team" action="index">Gestion des équipes</g:link></li>
        <li><g:link controller="team" action="showAdmin" id="${teamMember.team.id}">${teamMember.team.name}</g:link></li>
        <li><g:link action="show" id="${teamMember.id}">Membre: ${teamMember.name}</g:link></li>
        <li>Modifier</li>
    </ul>
</nav>

<g:panel title="Modifier un membre d'équipe">
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

    <g:uploadForm action="edit" id="${teamMember.id}" method="POST">
        <g:include view="teamMember/_form.gsp" model="${[teamMember: teamMember]}"/>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:uploadForm>
</g:panel>

</body>
</html>
