<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Modifier mot de passe</title>
</head>

<body>

<g:panel title="Modifier mot de passe">

    <g:if test="${request.method != 'GET'}">
        <g:hasErrors bean="${newPassword}">
            <ul class="errors" role="alert">
                <g:eachError bean="${newPassword}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                            error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
    </g:if>

    <g:form mapping="change_password" method="POST">
        <fieldset class="form">
            <f:with bean="${newPassword}">
                <f:field property="oldPassword" templates="user/password" />
                <f:field property="newPassword" templates="user/password" />
                <f:field property="newPasswordRepeat" templates="user/password" />
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="Mettre à jour"/>
        </fieldset>
    </g:form>

</g:panel>

</body>
</html>
