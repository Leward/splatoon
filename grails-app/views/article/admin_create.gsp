<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Créer un article</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_article">Article</g:link></li>
        <li>Créer</li>
    </ul>
</nav>

<g:panel title="Créer un article">

    <g:hasErrors bean="${article}">
        <ul class="errors" role="alert">
            <g:eachError bean="${article}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <form method="POST" action="">
        <fieldset class="form">
            <f:with bean="${article}">
                <f:field property="title"/>
                <f:field property="cover"/>
                <f:field property="category"/>
                <f:field property="content"/>
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save btn btn-primary" type="submit" value="Envoyer"/>
            <g:link mapping="admin_article">
                <button type="button" class="btn">Retour</button>
            </g:link>
        </fieldset>

    </form>

</g:panel>

</body>
</html>