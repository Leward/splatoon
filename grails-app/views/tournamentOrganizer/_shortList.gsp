<g:panel title="Organisateurs">
    <ul class="toto-list">
        <g:each in="${tournamentOrganizers}" var="organizer">
            <li>
                <g:link url="${organizer.website}">
                    ${organizer.name}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:panel>