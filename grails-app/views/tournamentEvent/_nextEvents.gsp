<%@ page import="java.time.format.DateTimeFormatter; splatoon.DateConversions" %>
<ul>
    <g:each in="${nextEvents}" var="event">
        <g:link mapping="tournament_event" id="${event.id}">
            <li class="event">

                <div class="date">
                    <div class="day">
                        <g:formatDate format="dd" date="${DateConversions.asDate(event.date)}"
                                      locale="fr"/>
                    </div>

                    <div class="month">
                        <g:formatDate format="MMMM" date="${DateConversions.asDate(event.date)}"
                                      locale="fr"/>
                    </div>
                </div>

                <div class="content">
                    <g:if test="${event.isLive()}">
                        <div class="live"><span class="badge">Live!</span></div>
                    </g:if>
                    <div class="name">${event.tournament.name}</div>

                    <div class="organizer">${event.tournament.organizer.name}</div>
                </div>
            </li>
        </g:link>
    </g:each>
</ul>
