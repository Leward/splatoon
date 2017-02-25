<%@ page import="java.time.format.DateTimeFormatter; splatoon.DateConversions" %>
<g:panel title="Événements à venir" class="next-events">
    <ul class="toto-list">
        <g:each in="${nextEvents}" var="event">
            <li class="event">

                <header class="date">
                    <g:link mapping="tournament_event" id="${event.id}">
                        <g:formatDate format="EEEE dd MMMM yyyy" date="${DateConversions.asDate(event.date)}"
                                      locale="fr"/>
                    </g:link>
                    <g:if test="${event.isLive()}">
                        <span class="live">Live</span>
                    </g:if>
                </header>

                <div class="time">
                    De <g:formatDate date="${DateConversions.asDate(event.date, event.startTime)}" format="HH:mm"/>
                    à <g:formatDate date="${DateConversions.asDate(event.date, event.endTime)}" format="HH:mm"/>
                </div>

                <div class="organizer">
                    Organisé par
                    <g:link controller="tournamentEvent" action="show" id="${event.id}">
                        ${event.tournament.name}
                    </g:link>
                </div>
            </li>
        </g:each>
    </ul>
</g:panel>
