<%@ page import="org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Repondre a une annonce de recrutement</title>
</head>

<body>

<g:panel title="Repondre a une annonce">
    <g:hasErrors bean="${adReply}">
        <ul class="errors" role="alert">
            <g:eachError bean="${adReply}" var="error">
                <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form method="POST" mapping="recruitment_reply_ad" id="${recruitingAd.id}">
        <fieldset class="form">
            <f:with bean="${adReply}">
                <g:hiddenField name="ad.id" value="${recruitingAd.id}"/>
                <f:field property="message"/>
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save btn btn-primary" type="submit" value="Envoyer"/>
        </fieldset>
    </g:form>
</g:panel>

</body>
</html>