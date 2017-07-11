<%@ page import="splatoon.AdType; org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Annonce de recrutement</title>
</head>

<body>

<div class="recruitment-ad-page">

    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>

    <div class="card">
        <header>Recrutements & Candidatures</header>
        <main>
            <h2 class="title">
                <g:if test="${recruitingAd.type == AdType.LOOKING_FOR_TEAM_AD}">
                    <g:set var="head" value="Recherche d'équipe"/>
                </g:if>
                <g:else>
                    Recherche de co-équipiers
                </g:else>
            </h2>

            <div class="row">
                <div class="col-sm-8 col-xs-7">
                    <h4>Pseudo</h4>

                    <p>
                        <g:if test="${recruitingAd.profileUrl}">
                            <g:link url="${recruitingAd.profileUrl}">${recruitingAd.author.username}</g:link>
                        </g:if>
                        <g:else>${recruitingAd.author.username}</g:else>
                    </p>
                    <h4>Date</h4>

                    <p><g:formatDate date="${recruitingAd.createdAtAsDate}" format="dd/MM/yyyy"/></p>
                </div>

                <div class="col-sm-4 col-xs-5 rank">${recruitingAd.rank.label}</div>
            </div>

            <div class="presentation">
                <h4>Présentation</h4>

                <div><g:html code="${recruitingAd.message}"/></div>

                <g:if test="${recruitingAd.canEdit()}">
                    <g:form mapping="recruitment_delete_ad" id="${recruitingAd.id}" method="POST">
                        <fieldset class="buttons">
                            <div class="text-right">
                                <g:link class="btn btn-primary" mapping="recruitment_edit_ad"
                                        id="${recruitingAd.id}">Modifier</g:link>
                                <button type="submit" class="delete btn btn-danger"
                                        onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette annonce ? Cette action est irréversible. ');">
                                    Supprimer
                                </button>
                            </div>
                        </fieldset>
                    </g:form>

                </g:if>
            </div>

        </main>
    </div>

    <div class="card">
        <header>Réponses</header>
        <main>
            <g:each in="${replies}" var="reply">
                <div class="reply">
                    <g:if test="${reply.canEdit()}">
                        <g:form mapping="recruitment_reply_ad_delete" id="${reply.id}" method="POST" style="float: right;">
                            <fieldset class="buttons">
                                <div class="text-right">
                                    <g:link class="btn btn-primary btn-xs" mapping="recruitment_reply_ad_edit" id="${reply.id}">Modifier</g:link>
                                    <button type="submit" class="delete btn btn-danger btn-xs"
                                            onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce commentaire ? Cette action est irréversible. ');">
                                        Supprimer
                                    </button>
                                </div>
                            </fieldset>
                        </g:form>
                    </g:if>
                    <h4>${reply.author}</h4>

                    <div>
                        <g:html code="${reply.message}"/>
                    </div>

                </div>
            </g:each>
        </main>
    </div>

    <sec:ifLoggedIn>
        <div class="card">
            <header>Répondre</header>
            <main>
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
            </main>
        </div>
    </sec:ifLoggedIn>

</div>

%{--<div class="row">--}%
%{--<g:if test="${recruitingAd.type == AdType.LOOKING_FOR_TEAM_AD}">--}%
%{--<g:set var="head" value="${recruitingAd.author.username} cherche une equipe"/>--}%
%{--</g:if>--}%
%{--<g:if test="${recruitingAd.type == AdType.LOOKING_FOR_TEAMMATE_AD}">--}%
%{--<g:set var="head" value="${recruitingAd.author.username} cherche des co-equipiers"/>--}%
%{--</g:if>--}%

%{--<g:panel title="Annonce : ${head}">--}%
%{--<h3>${recruitingAd.title}</h3>--}%

%{--<g:html code="${recruitingAd.message}"/>--}%

%{--<p><g:if test="${recruitingAd.profileUrl}">--}%
%{--Profile : <g:link url="${recruitingAd.profileUrl}">--}%
%{--${recruitingAd.profileUrl}--}%
%{--</g:link>--}%
%{--</g:if></p>--}%

%{--<p>--}%
%{--Poste par ${recruitingAd.author.username}--}%
%{--le <g:formatDate date="${recruitingAd.createdAtAsDate}" format="dd/MM/yyyy"/>--}%
%{--</p>--}%
%{--</g:panel>--}%

%{--<g:panel title="Discussion">--}%
%{--<g:each in="${replies}" var="reply">--}%
%{--<div>--}%
%{--<g:html code="${reply.message}"/>--}%
%{--</div>--}%
%{--</div>--}%
%{--<p>--}%
%{--Par ${reply.author.username}--}%
%{--<g:if test="${reply.canEdit()}">--}%
%{--- <g:link mapping="recruitment_reply_ad_edit" id="${reply.id}">Modifier</g:link>--}%
%{--</g:if>--}%
%{--</p>--}%
%{--</g:each>--}%
%{--</g:panel>--}%



%{--</div>--}%

</body>
</html>