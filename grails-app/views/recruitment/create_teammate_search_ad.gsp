<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Poster une annonce : Recherche de co-équipier</title>
</head>

<body>

<g:panel title="Nouvelle annonce : Recherche de co-équipier">

    <g:hasErrors bean="${recruitingAd}">
        <ul class="errors" role="alert">
            <g:eachError bean="${recruitingAd}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <form method="POST" action="">
        <fieldset class="form">
            <f:with bean="${recruitingAd}">
                <f:field property="title"/>
                <f:field property="rank"/>
                <f:field property="message"/>
                <f:field property="profileUrl"/>
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save btn btn-primary" type="submit" value="Envoyer"/>
            <g:link mapping="recruitment"><button type="button" class="btn">Retour</button></g:link>
        </fieldset>

    </form>
</g:panel>

</body>
</html>