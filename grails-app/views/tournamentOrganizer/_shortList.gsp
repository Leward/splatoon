<div class="panel next-events">
    <div class="panel-header">
        <h3><g:link controller="tournamentOrganizer" action="index">Organisateurs</g:link></h3>
    </div>

    <div class="panel-body">
        <ul class="toto-list">
            <g:each in="${tournamentOrganizers}" var="organizer">
                <li>
                    <g:link url="${organizer.website}">
                        ${organizer.name}
                    </g:link>
                </li>
            </g:each>
        </ul>
    </div>
</div>