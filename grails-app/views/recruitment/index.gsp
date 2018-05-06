<%@ page import="splatoon.HtmlStringUtils" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Recrutement</title>
</head>

<body>

<g:if test="${flash.message}">
    <div class="alert alert-info">
        ${flash.message}
    </div>
</g:if>

<div class="card">
    <header class="blue">
        <h2>Poster une annonce</h2>
    </header>
    <main>
        <p>
            Je cherche une team: <g:link mapping="update_information">Indiquez-le dans votre profile joueur</g:link>
        </p>

        <p><g:link mapping="recruitment_new_teammate_search">Je recrute pour une team</g:link></p>
    </main>
</div>

<div class="row">
    <div class="col-sm-6">
        <g:panel title="Ils recherchent une team" color="red" class="recruitment">
            <ul>
                <g:each in="${usersLookingForATeam}" var="user">
                    <li class="ad">
                        <div class="avatar">
                            <g:if test="${user.avatar}">
                                <g:img uri="${user.avatar}" alt="Avatar de ${user.username}"/>
                            </g:if>
                            <g:else>
                                <g:img file="spash_avatar-44x45.png" alt="Avatar par defaut"/>
                            </g:else>
                        </div>
                        <h4 class="title">
                            %{-- Keep g:if at the end of the line so as not to create an undesired whitespace--}%
                            ${user.username}, rang ${user.playerProfile.rank}<g:if
                                    test="${user.playerProfile.lookingForCompetition}">,
                                            recherche une equipe ${user.playerProfile.competitionTypes.join(' et ')}
                            </g:if>
                        </h4>

                        <p class="intro">
                            ${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: user.playerProfile.presentation, strict: true).toString(), 50))}
                        </p>

                        <div class="read-more">
                            <g:link mapping="profile" id="${user.id}" params="${[slug: user.slug]}">
                                en lire +
                            </g:link>
                        </div>
                    </li>
                </g:each>
            </ul>
        </g:panel>
    </div>

    <div class="col-sm-6">
        <g:panel title="Teams qui recrutent" color="red" class="recruitment">
            <ul>
                <g:each in="${searchTeammateAds}" var="ad">
                    <li class="ad">
                        <div class="avatar">
                            <g:img file="spash_avatar-44x45.png" alt="Avatar par defaut"/>
                        </div>
                        <h4 class="title">${ad.title}</h4>

                        <p class="intro">
                            ${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: ad.message, strict: true).toString(), 50))}
                        </p>

                        <div class="read-more">
                            <g:link mapping="recruitment_show_ad" id="${ad.id}">
                                en lire +
                            </g:link>
                        </div>
                    </li>
                </g:each>
            </ul>
        </g:panel>
    </div>
</div>

</body>
</html>