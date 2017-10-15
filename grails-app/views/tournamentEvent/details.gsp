<%@ page import="java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${tournamentEvent.tournament.name}</title>
</head>

<body>

<div class="event-details-page">

    <g:if test="${flash.message}">
        <div class="alert alert-info" role="status">${flash.message}</div>
    </g:if>

    <div class="card event-details">
        <main>
            <h2 class="name">${tournamentEvent.tournament.name}</h2>

            <div class="organizer">
                <g:link url="${tournamentEvent.tournament.organizer.website}">
                    ${tournamentEvent.tournament.organizer.name}
                </g:link>
            </div>
            <dl>
                <dt>Jeu</dt>
                <dd>${tournamentEvent.tournament.game}</dd>
                <dt>Date</dt>
                <dd>${tournamentEvent.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE))}</dd>
                <dt>Début</dt>
                <dd>${tournamentEvent.startTime}</dd>
                <dt>Fin</dt>
                <dd>${tournamentEvent.endTime}</dd>
            </dl>


            <div class="rules">
                <div class="title">Modalités d'inscription</div>


                <div class="content read-more">
                    <g:if test="${tournamentEvent.tournament.registrationDetails}">
                        <g:enforceResponsiveContent>
                            <g:html code="${tournamentEvent.tournament.registrationDetails}"/>
                        </g:enforceResponsiveContent>
                    </g:if>
                    <g:else>
                        <p>Contacter l'organisateur pour s'inscrire au tournoi.</p>
                    </g:else>
                </div>
            </div>

            <div class="rules">
                <div class="title">Règles</div>

                <div class="content read-more">
                    <g:enforceResponsiveContent>
                        <g:html code="${tournamentEvent.tournament.rules}"/>
                    </g:enforceResponsiveContent>
                </div>
            </div>
        </main>
    </div>

    <g:if test="${tournamentEvent.managedRegistrations}">
        <g:panel title="Inscription">
            <p>
                Les inscriptions pour ce tournoi se font sur Inkzone et sont
                <strong>${tournamentEvent.isPublicRegistrationOpen() ? 'ouvertes' : 'fermees'}</strong>.
            </p>
            <sec:ifNotLoggedIn>
                <p>Veuillez vous connecter pour inscrire votre equipe au tournoi.</p>
            </sec:ifNotLoggedIn>

            <sec:ifLoggedIn>
                <g:if test="${registration}">
                    <p>Votre equipe <strong>${registration.team}</strong> est inscrite au tournoi.</p>
                    <g:if test="${tournamentEvent.isPublicRegistrationOpen()}">
                        <g:form controller="tournamentRegistration" action="unregister" id="${registration.id}">
                            <button type="submit" class="btn btn-danger"
                                    onclick="return confirm('En etes-vous sur ?');" >
                                Se desinscrire du tournoi</button>
                        </g:form>
                    </g:if>
                </g:if>
                <g:else>
                    <p><g:link mapping="tournament_event_registration" id="${tournamentEvent.id}"
                               class="btn btn-primary">Inscrire mon equipe</g:link></p>
                </g:else>
            </sec:ifLoggedIn>
        </g:panel>
    </g:if>

    <g:panel title="Règles" class="event-details">
        <div class="rules">
            <div class="title">Modalités d'inscription</div>

            <div class="content read-more">
                <g:if test="${tournamentEvent.tournament.registrationDetails}">
                    <g:enforceResponsiveContent>
                        <g:html code="${tournamentEvent.tournament.registrationDetails}"/>
                    </g:enforceResponsiveContent>
                </g:if>
                <g:else>
                    <p>Contacter l'organisateur pour s'inscrire au tournoi.</p>
                </g:else>
            </div>
        </div>

        <div class="rules">
            <div class="title">Règles</div>

            <div class="content read-more">
                <g:enforceResponsiveContent>
                    <g:html code="${tournamentEvent.tournament.rules}"/>
                </g:enforceResponsiveContent>
            </div>
        </div>
    </g:panel>

    <g:if test="${tournamentEvent.streamUrl != null}">
        <div class="card no-padding">
            <main>
                <div class="stream">
                    <g:stream event="${tournamentEvent}"/>
                </div>
            </main>
        </div>
    </g:if>

    <g:if test="${tournamentEvent.challongeUrl != null}">
        <div class="card no-padding">
            <header>Résultats</header>
            <main><g:challonge url="${tournamentEvent.challongeUrl}"/></main>
        </div>
    </g:if>

</div>
</body>
</html>
