<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mettre a jour mes informations</title>
</head>

<body>

<g:panel title="Mettre a jour mes informations">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${playerProfile}">
        <ul class="errors" role="alert">
            <g:eachError bean="${playerProfile}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form method="POST" mapping="update_information">
        <fieldset class="form">
           <f:with bean="playerProfile">
               <f:field property="nintendoId"/>
               <f:field property="birthDate"/>
               <f:field property="availability"/>
               <f:field property="rank"/>
               <f:field property="mainWeaponCategory"/>
               <f:field property="roles"/>
               <f:field property="alreadyInATeam"/>
               <f:field property="lookingForATeam"/>
               <f:field property="lookingForFunCompetition"/>
               <f:field property="lookingForProCompetition"/>
           </f:with>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save btn btn-primary full-width" value="Mettre a jour"/>
        </fieldset>
    </g:form>

</g:panel>


</body>
</html>
