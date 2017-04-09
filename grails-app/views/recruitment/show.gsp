<%@ page import="org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Annonce de recrutement</title>
</head>

<body>

<g:if test="${recruitingAd.type == splatoon.AdType.LOOKING_FOR_TEAM_AD}">
    <g:set var="head" value="${recruitingAd.author.username} cherche une equipe"/>
</g:if>
<g:if test="${recruitingAd.type == splatoon.AdType.LOOKING_FOR_TEAMMATE_AD}">
    <g:set var="head" value="${recruitingAd.author.username} cherche des co-equipiers"/>
</g:if>

<g:panel title="Annonce : ${head}">
    <h3>${recruitingAd.title}</h3>

    <p>${recruitingAd.message}</p>

    <p><g:if test="${recruitingAd.profileUrl}">
        Profile : <g:link url="${recruitingAd.profileUrl}">
            ${recruitingAd.profileUrl}
        </g:link>
    </g:if></p>

    <p>
        Poste par ${recruitingAd.author.username}
        le <g:formatDate date="${recruitingAd.createdAtAsDate}" format="dd/MM/yyyy"/>
    </p>
</g:panel>

<g:panel title="Discussion">
    <g:each in="${replies}" var="reply">
    <p>${reply.message}</p>
    <p>
        Par ${reply.author.username}
        <g:if test="${reply.canEdit()}">
            - <g:link mapping="recruitment_reply_ad_edit" id="${reply.id}">Modifier</g:link>
        </g:if>
    </p>
    </g:each>
</g:panel>

<sec:ifLoggedIn>
    <g:panel title="Repondre a l'annonce">
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
</sec:ifLoggedIn>

</body>
</html>