<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mot de passe oubié ? </title>
</head>

<body>

<g:panel title="Mot de passe oublié">

    <g:if test="${request.method != 'GET'}">
        <g:hasErrors bean="${newPasswordRequest}">
            <ul class="errors" role="alert">
                <g:eachError bean="${newPasswordRequest}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                            error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
    </g:if>

    <g:form mapping="forgotten_password" method="POST">
        <fieldset class="form">
            <f:with bean="${newPasswordRequest}">
                <f:field property="email"/>
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="M'envoyer un nouveau de passe"/>
        </fieldset>
    </g:form>

</g:panel>

</body>
</html>
