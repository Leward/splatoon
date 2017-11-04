<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gérer une équipe</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link action="index">Gestion des équipes</g:link></li>
        <li>${team.name}</li>
    </ul>
</nav>

<g:panel title="Équipe ${team.name}">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="team"/>
    <g:form resource="${this.team}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="btn btn-primary" action="edit" resource="${this.team}"><g:message code="default.button.edit.label"
                                                                                  default="Edit"/></g:link>
            <input class="btn btn-danger" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>
