<div class="checkbox">
    <g:each in="${splatoon.PlayerRole.values()}" var="playerRole">
        <label>
            <input
                    type="checkbox"
                    id="${property}-${playerRole.name()}"
                    name="${property}"
                    value="${playerRole.name()}"
                    ${value?.contains(playerRole) ? 'checked' : ''}>
            <g:message code="${playerRole.i18nMessage}" />
        </label>
    </g:each>
</div>
