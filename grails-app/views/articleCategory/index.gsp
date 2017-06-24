<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Catégories d'Article</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Catégories d'Article</li>
    </ul>
</nav>

<g:panel title="Catégories d'Article">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <p>
        <g:link class="btn btn-primary" action="create">
            Ajouter une catégorie
        </g:link>
    </p>

    <f:table collection="${articleCategoryList}"/>

    <div class="pagination">
        <g:paginate total="${articleCategoryCount ?: 0}"/>
    </div>
</g:panel>

</body>
</html>