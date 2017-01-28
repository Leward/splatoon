<%@ page import="java.time.format.DateTimeFormatter; splatoon.DateConversions" %>
<div class="panel next-events">
    <div class="panel-header">
        <h3>Événements à venir</h3>
    </div>

    <div class="panel-body">
        %{--<table>--}%
        %{--<tbody>--}%
        %{--<g:each in="${nextEvents}" var="event">--}%
        %{--<tr>--}%
        %{--<td>${event.date}</td>--}%
        %{--<td>${event.startTime} -> ${event.endTime}</td>--}%
        %{--<td>--}%
        %{--<g:link controller="tournamentEvent" action="show" id="${event.id}">--}%
        %{--${event.tournament.name}--}%
        %{--</g:link>--}%
        %{--</td>--}%
        %{--</tr>--}%
        %{--</g:each>--}%
        %{--</tbody>--}%
        %{--</table>--}%

        <ul class="toto-list">
            <g:each in="${nextEvents}" var="event">
                <li>

                    <header class="date">
                        <g:formatDate format="EEEE dd MMMM yyyy" date="${DateConversions.asDate(event.date)}"
                                      locale="fr"/>
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

    </div>
</div>