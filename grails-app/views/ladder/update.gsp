<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion du Ladder</title>
</head>

<body>

<div class="ladder-page">

    <nav>
        <ul class="breadcrumb">
            <li><g:link mapping="admin">Administration</g:link></li>
            <li><g:link mapping="admin_ladder">Gestion du Ladder</g:link></li>
            <li>
                <g:link mapping="admin_ladder_event_details" id="${ladder.event.id}">
                    ${ladder.event.tournament.name}
                </g:link>
            </li>
            <li>Modifier un résultat</li>
        </ul>
    </nav>

    <div class="card">
        <header><h2>Modifier un résultat</h2></header>
        <main>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.ladder}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.ladder}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            <g:form mapping="admin_ladder_update_result" id="${ladder.id}" method="post">
                <fieldset class="form">
                    <f:with bean="ladder">
                        <f:field property="team" />
                        <f:field property="wins"/>
                        <f:field property="loses"/>
                        <f:field property="points"/>
                    </f:with>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save"
                                    value="Mettre à jour"/>
                </fieldset>
            </g:form>
        </main>
    </div>

</div>

</body>
</html>