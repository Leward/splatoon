<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Creer une news</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_news">News</g:link></li>
        <li>Creer</li>
    </ul>
</nav>

<g:panel title="Creer une news">

    <g:hasErrors bean="${news}">
        <ul class="errors" role="alert">
            <g:eachError bean="${news}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <form method="POST" action="">
        <fieldset class="form">
            <f:with bean="${news}">
                <f:field property="title"/>
                <f:field property="content"/>
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save btn btn-primary" type="submit" value="Envoyer"/>
            <g:link mapping="admin_news">
                <button type="button" class="btn">Retour</button>
            </g:link>
        </fieldset>

    </form>

</g:panel>

</body>
</html>