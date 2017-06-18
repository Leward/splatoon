<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Modifier une news: ${news.title}</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_news">News</g:link></li>
        <li><g:link mapping="admin_news_show" id="${news.id}">${news.title}</g:link></li>
        <li>Modifier</li>
    </ul>
</nav>

<g:panel title="Modifier une news">

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
                <f:field property="cover"/>
                <f:field property="content"/>
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save btn btn-primary" type="submit" value="Envoyer"/>
            <g:link mapping="admin_news_show" id="${news.id}">
                <button type="button" class="btn">Retour</button>
            </g:link>
        </fieldset>

    </form>

</g:panel>

</body>
</html>