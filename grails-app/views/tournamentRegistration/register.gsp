<%@ page import="java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inscription a un tournoi</title>
</head>

<body>

<div class="event-details-page">

    <g:panel title="Inscription au tournoi">

        <g:hasErrors bean="${this.tournamentRegistration}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.tournamentRegistration}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                            error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>

        <g:form method="post" mapping="tournament_event_registration" id="${tournamentRegistration.event.id}">
            <fieldset class="form">
                <p>
                    <strong>Tournoi :</strong>
                    <g:link mapping="tournament_event" id="${tournamentRegistration.event.id}">
                        ${tournamentRegistration.event}
                    </g:link>
                </p>

                <p>
                    <label for="team.id">Equipe a inscrire:</label>
                    <g:select id="team.id" name="team.id" from="${teams}" optionKey="id" value="${tournamentRegistration.team?.id}"/>
                </p>
            </fieldset>


            <fieldset class="buttons">
                <button type="submit" class="btn btn-primary">Inscrire l'equipe</button>
                <g:link mapping="tournament_event" id="${tournamentRegistration.event.id}" class="btn btn-default">
                    Retour
                </g:link>
            </fieldset>
        </g:form>

    </g:panel>

</div>
</body>
</html>
