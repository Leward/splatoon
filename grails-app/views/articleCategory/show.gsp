<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gérer une catégorie d'article</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link action="index">Catégories d'Article</g:link></li>
        <li>${articleCategory.name}</li>
    </ul>
</nav>

<g:panel title="Gérer une catégorie">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="articleCategory"/>
    <g:form resource="${this.articleCategory}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.articleCategory}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</g:panel>
</body>
</html>
