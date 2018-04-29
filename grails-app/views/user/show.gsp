<%@ page import="splatoon.PlayerProfile; splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Profile ${user.username}</title>
</head>

<body>

<g:panel title="Profile joueur de ${user.username}" color="blue" circle="true" class="player-profile">
    <h3>General</h3>

    <div class="row">
        <div class="col-sm-8 col-xs-7">
            <dl>
                <dt>${user.username}</dt>
                <dd>${user.playerProfile?.age ?: '?'} ans</dd>
                <dt>Identifiant Nintendo</dt>
                <dd>${user.playerProfile?.nintendoId ?: 'Inconnu'}</dd>
                <dt><g:message code="playerProfile.availability.label"/></dt>
                <dd>${user.playerProfile?.availability ?: 'Non specifie'}</dd>
                <dt>Presentation</dt>
                <dd>
                    <g:html code="${user.playerProfile.presentation}" />
                </dd>
            </dl>
        </div>

        <div class="col-sm-4 col-xs-5 rank">
            ${user.playerProfile?.rank ?: PlayerProfile.DEFAULT_RANK}
        </div>
    </div>

    <hr>

    <h3>Type de jeu</h3>
    <dl>
        <dt>Type d'arme principale</dt>
        <dd>
            <g:if test="${user.playerProfile?.mainWeaponCategory}">
                <g:message code="weapon.category.${user.playerProfile?.mainWeaponCategory}"/>
            </g:if>
            <g:else>
                Inconnu
            </g:else>
        </dd>
        <dt>Roles</dt>
        <dd>
            <g:if test="${user.playerProfile == null || user.playerProfile?.roles.size() == 0}">
                Inconnu
            </g:if>
            <g:else>
                <g:each in="${user.playerProfile?.roles}" var="role" status="i">
                    <g:message code="${role.i18nMessage}"/><g:if
                        test="${i < user.playerProfile.roles.size() - 1}">,</g:if>
                </g:each>
            </g:else>
        </dd>
    </dl>

    <hr>

    <h3>Équipe et compétition</h3>
    <dl>
        <dt>Équipe</dt>
        <dd>
            <g:if test="${user.playerProfile.alreadyInATeam}">
                Joue dans une equipe
                <g:if test="${user.playerProfile.lookingForATeam}">
                    et recherche une nouvelle équipe
                </g:if>
            </g:if>
            <g:else>
                <g:if test="${user.playerProfile.lookingForATeam}">
                    Recherche une équipe
                </g:if>
                <g:else>
                    N'a pas d'équipe
                </g:else>
            </g:else>
        </dd>
        <dt>Compétitions</dt>
        <dd>
            <g:if test="${!user.playerProfile.lookingForProCompetition && !user.playerProfile.lookingForProCompetition}">
                N'est pas interessé par la compétitions
            </g:if>
            <g:if test="${user.playerProfile.lookingForProCompetition && user.playerProfile.lookingForProCompetition}">
                Interessé par les competitions Pro et Fun
            </g:if>
            <g:if test="${!user.playerProfile.lookingForProCompetition && user.playerProfile.lookingForProCompetition}">
                Interessé par les competitions Fun uniquement
            </g:if>
            <g:if test="${user.playerProfile.lookingForProCompetition && !user.playerProfile.lookingForProCompetition}">
                Interessé par les competitions Pro uniquement
            </g:if>
        </dd>
    </dl>
</g:panel>

<g:if test="${user.teams}">
    <g:panel title="Equipes" color="red" circle="true">
        <ul>
            <g:each in="${user.teams}" var="team">
                <li>${team}</li>
            </g:each>
        </ul>
    </g:panel>
</g:if>

</body>
</html>
