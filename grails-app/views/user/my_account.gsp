<%@ page import="splatoon.PlayerProfile; splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mon Espace</title>
</head>

<body>

<g:panel title="Mon espace">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <p>Bienvenue, ${me.username} !</p>

    <ul>
        <li><g:link mapping="change_password">Changer de mot de passe</g:link></li>
        <li><g:link mapping="update_information">Modifier mes informations</g:link></li>
        <li><g:link mapping="avatar">Modifier mon avatar</g:link></li>
    </ul>
</g:panel>

<g:panel title="Mon profile joueur" color="blue" circle="true" class="player-profile">
    <h3>General</h3>

    <div class="row">
        <div class="col-sm-8 col-xs-7">
            <dl>
                <dt>Identifiant Nintendo</dt>
                <dd>${me.playerProfile?.nintendoId ?: 'Inconnu'}</dd>
                <dt><g:message code="playerProfile.availability.label"/></dt>
                <dd>${me.playerProfile?.availability ?: 'Non specifie'}</dd>
                <dt>Presentation</dt>
                <dd>
                    <g:html code="${me.playerProfile.presentation ?: ''}"/>
                </dd>
            </dl>
        </div>

        <div class="col-sm-4 col-xs-5">
            <g:if test="${me.avatar}">
                <div class="avatar">
                    <img src="${me.avatar}" alt="Avatar de ${me.username}">
                </div>
            </g:if>
            <dl>
                <dt>${me.username}</dt>
                <dd>${me.playerProfile?.age ?: '?'} ans</dd>
            </dl>
            <div class="rank">
                <strong>Rang</strong>
                <span>${me.playerProfile?.rank ?: PlayerProfile.DEFAULT_RANK}</span>
            </div>
        </div>
    </div>

    <hr>

    <h3>Type de jeu</h3>
    <dl>
        <dt>Type d'arme principale</dt>
        <dd>
            <g:if test="${me.playerProfile?.mainWeaponCategory}">
                <g:message code="weapon.category.${me.playerProfile?.mainWeaponCategory}"/>
            </g:if>
            <g:else>
                Inconnu
            </g:else>
        </dd>
        <dt>Roles</dt>
        <dd>
            <g:if test="${me.playerProfile == null || me.playerProfile?.roles.size() == 0}">
                Inconnu
            </g:if>
            <g:else>
                <g:each in="${me.playerProfile?.roles}" var="role" status="i">
                    <g:message code="${role.i18nMessage}"/><g:if
                        test="${i < me.playerProfile.roles.size() - 1}">,</g:if>
                </g:each>
            </g:else>
        </dd>
    </dl>

    <hr>

    <h3>Équipe et compétition</h3>
    <dl>
        <dt>Équipe</dt>
        <dd>
            <g:if test="${me.playerProfile.alreadyInATeam}">
                Joue dans une equipe
                <g:if test="${me.playerProfile.lookingForATeam}">
                    et recherche une nouvelle équipe
                </g:if>
            </g:if>
            <g:else>
                <g:if test="${me.playerProfile.lookingForATeam}">
                    Recherche une équipe
                </g:if>
                <g:else>
                    N'a pas d'équipe
                </g:else>
            </g:else>
        </dd>
        <dt>Compétitions</dt>
        <dd>
            <g:if test="${!me.playerProfile.lookingForProCompetition && !me.playerProfile.lookingForProCompetition}">
                N'est pas interessé par la compétitions
            </g:if>
            <g:if test="${me.playerProfile.lookingForProCompetition && me.playerProfile.lookingForProCompetition}">
                Interessé par les competitions Pro et Fun
            </g:if>
            <g:if test="${!me.playerProfile.lookingForProCompetition && me.playerProfile.lookingForProCompetition}">
                Interessé par les competitions Fun uniquement
            </g:if>
            <g:if test="${me.playerProfile.lookingForProCompetition && !me.playerProfile.lookingForProCompetition}">
                Interessé par les competitions Pro uniquement
            </g:if>
        </dd>
    </dl>
</g:panel>

<g:if test="${me.teams}">
    <g:panel>
        <h2>Mes equipes</h2>
        <ul>
            <g:each in="${me.teams}" var="team">
                <li>${team}</li>
            </g:each>
        </ul>
    </g:panel>
</g:if>

</body>
</html>
