<div class="checkbox">
    <g:each in="${splatoon.PlayerRole.values()}" var="playerRole">
        <label>
            <input
                    type="checkbox"
                    id="${property}-${playerRole.name()}"
                    name="${property}"
                    value="${playerRole.name()}"
                    ${value?.contains(playerRole) ? 'checked' : ''}>
            ${playerRole.name()}
        </label>
    </g:each>
</div>
