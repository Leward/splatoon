<%@ page import="splatoon.HtmlStringUtils" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Recherche de co-équipier</title>
</head>

<body>

<g:panel title="Recherche de co-équipier">
    <g:form mapping="recruitment_search_players">
        <f:with bean="${search}">
            <f:field property="minRank"/>
            <f:field property="minAge"/>
            <f:field property="mainWeaponCategory"/>
            <f:field property="funCompetition"/>
            <f:field property="proCompetition"/>
        </f:with>
        <button type="submit">Rechercher</button>
    </g:form>
</g:panel>

<g:panel title="Résultats" class="recruitment">
    <table>
        <thead>
        <tr>
            <th rowspan="2">Joueur</th>
            <th rowspan="2">Rang</th>
            <th rowspan="2">Age</th>
            <th rowspan="2" class="updated-at">MaJ</th>
            <th colspan="2">Tournois</th>
        </tr>
        <tr>
            <th>Pro</th>
            <th>Fun</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${users}" var="user">
            <tr>
                <td>
                    <div class="avatar">
                        <g:link mapping="profile" id="${user.id}" params="${[slug: user.slug]}">
                        <g:if test="${user.avatar}">
                            <g:img uri="${user.avatar}" alt="Avatar de ${user.username}"/>
                        </g:if>
                        <g:else>
                            <g:img file="spash_avatar-44x45.png" alt="Avatar par defaut"/>
                        </g:else>
                        ${user.username}
                        </g:link>
                    </div>
                </td>
                <td>${user.playerProfile.rank}</td>
                <td>${user.playerProfile.age}</td>
                <td class="updated-at">
                    <g:formatDate format="dd/MM/yyyy" date="${Date.from(user.playerProfile.updatedAt)}" />
                </td>
                <td>${user.playerProfile.lookingForFunCompetition ? 'X' : ''}</td>
                <td>${user.playerProfile.lookingForProCompetition ? 'X' : ''}</td>
            </tr>
        %{--<li class="ad">--}%

        %{--<h4 class="title">--}%
        %{-- Keep g:if at the end of the line so as not to create an undesired whitespace--}%
        %{--${user.username}, rang ${user.playerProfile.rank}<g:if--}%
        %{--test="${user.playerProfile.lookingForCompetition}">,--}%
        %{--recherche une equipe ${user.playerProfile.competitionTypes.join(' et ')}--}%
        %{--</g:if>--}%
        %{--</h4>--}%

        %{--<p class="intro">--}%
        %{--${raw(HtmlStringUtils.truncateHtml(excerptHtml(code: user.playerProfile.presentation, strict: true).toString(), 50))}--}%
        %{--</p>--}%

        %{--<div class="read-more">--}%
        %{--<g:link mapping="profile" id="${user.id}" params="${[slug: user.slug]}">--}%
        %{--en lire +--}%
        %{--</g:link>--}%
        %{--</div>--}%
        %{--</li>--}%
        </g:each>
        </tbody>
    </table>
</g:panel>

</body>
</html>