<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Annonce de recrutement</title>
</head>

<body>

<g:if test="${recruitingAd.type == splatoon.AdType.LOOKING_FOR_TEAM_AD}">
    <g:set var="head" value="${recruitingAd.author.username} cherche une equipe" />
</g:if>
<g:if test="${recruitingAd.type == splatoon.AdType.LOOKING_FOR_TEAMMATE_AD}">
    <g:set var="head" value="${recruitingAd.author.username} cherche des co-equipiers" />
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

</body>
</html>