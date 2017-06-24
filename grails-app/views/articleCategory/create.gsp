<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Créer une catégorie</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link action="index">Catégories d'Article</g:link></li>
        <li>Ajouter</li>
    </ul>
</nav>

<g:panel title="Créer une catégorie">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.articleCategory}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.articleCategory}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.articleCategory}" method="POST">
        <fieldset class="form">
            <f:all bean="articleCategory"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</g:panel>
</body>
</html>
