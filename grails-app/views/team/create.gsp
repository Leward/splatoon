<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ajouter une équipe</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link action="index">Gestion des équipes</g:link></li>
        <li>Ajouter</li>
    </ul>
</nav>


<g:panel title="Ajouter une équipe">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${this.team}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.team}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="create" method="POST">
        <fieldset class="form">
            <f:all bean="team"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="Ajouter"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>
