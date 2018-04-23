<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
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
    </ul>
</g:panel>

<g:panel title="Mon profile joueur">
    <h3>General</h3>
    <dl>
        <dt>Pseudo :</dt>
        <dd>${me.username}</dd>
        <dt>Identifiant Nintendo :</dt>
        <dd>${me.playerProfile?.nintendoId ?: 'Inconnu'}</dd>
        <dt>Age</dt>
        <dd>${me.playerProfile?.age ?: 'Inconnu'}</dd>
        <dt><g:message code="playerProfile.availability.label" /></dt>
        <dd>${me.playerProfile.availability}</dd>
    </dl>

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

    <h3>Équipe et compétition</h3>
    <dl>
        <dt>Joue dans une equipe</dt>
        <dd>${me.playerProfile?.alreadyInATeam ? 'Oui' : 'Non'}</dd>
        <dt>Recherche une équipe</dt>
        <dd>${me.playerProfile?.lookingForATeam ? 'Oui' : 'Non'}</dd>
        <dt>Souhaite jouer des compétitions fun</dt>
        <dd>${me.playerProfile?.lookingForFunCompetition ? 'Oui' : 'Non'}</dd>
        <dt>Souhaite jouer des compétitions pro</dt>
        <dd>${me.playerProfile?.lookingForProCompetition ? 'Oui' : 'Non'}</dd>
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
