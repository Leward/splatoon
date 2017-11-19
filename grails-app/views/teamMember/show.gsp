<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gérer un joueur</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link controller="team" action="index">Gestion des équipes</g:link></li>
        <li><g:link controller="team" action="showAdmin" id="${teamMember.team.id}">${teamMember.team.name}</g:link></li>
        <li>Membre: ${teamMember.name}</li>
    </ul>
</nav>

<g:panel title="Joueur ${teamMember.name}">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="teamMember"/>
    <g:form resource="${this.teamMember}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="btn btn-primary" action="edit" resource="${this.teamMember}"><g:message code="default.button.edit.label"
                                                                                                   default="Edit"/></g:link>
            <input class="btn btn-danger" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>
