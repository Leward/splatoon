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

    <g:form method="POST" mapping="update_information">
        <fieldset class="form">
           %{--<f:all bean="information"/>--}%
           <f:with bean="information">
               <f:field property="nintendoId"/>
           </f:with>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save btn btn-primary full-width" value="Mettre a jour"/>
        </fieldset>
    </g:form>

</g:panel>


</body>
</html>
