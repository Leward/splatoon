<%@ page import="java.text.DateFormat; java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${tournamentEvent.tournament.name}</title>
</head>

<body>

<div class="event-details-page">

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
