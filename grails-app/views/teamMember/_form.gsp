<g:hiddenField name="version" value="${this.teamMember?.version}"/>
<fieldset class="form">
    <f:with bean="teamMember">
        <f:field property="team"/>
        <f:field property="name"/>
        <f:field property="type"/>
        <f:field property="roles"/>
        <f:field property="file"/>
        <div class="alert alert-info">Les images sont automatiquement redimensionnees a la taille 110x115</div>
        <g:if test="${teamMember.avatar}">
            <img src="${teamMember.avatar}" alt="Avatar" />
        </g:if>
    </f:with>
</fieldset>