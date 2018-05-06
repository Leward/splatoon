<%@ page import="splatoon.PlayerProfile; splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mon Espace - Avatar</title>
</head>

<body>

<g:panel title="Modifier mon avatar">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <p>Sur cette page, vous pouvez modifier votre avatar.</p>

    <g:hasErrors bean="${avatar}">
        <ul class="errors" role="alert">
            <g:eachError bean="${avatar}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:uploadForm method="POST" mapping="avatar" >
        <f:with bean="${avatar}">
            <f:field property="file" />
        </f:with>
        <button type="submit">Upload</button>
    </g:uploadForm>

</g:panel>

</body>
</html>
