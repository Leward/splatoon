<g:panel title="Pas encore de profil ?" class="register">

    <p>En vous créant un compte, vous pourrez profiter des fonctions du Splat Portail
    telles que poster des offres et demandes dans l'espace de recrutement.</p>

    <g:hasErrors bean="${newUser}">
        <div class="alert alert-danger">
            <ul class="errors">
                <g:eachError bean="${newUser}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                        <g:message error="${error}"/>
                    </li>
                </g:eachError>
            </ul>
        </div>
    </g:hasErrors>

    <div class="width-80p">

        <form method="post" action="">
            <fieldset class="form">
                <f:with bean="newUser">
                    <f:field property="username"/>
                    <f:field property="email"/>
                    <f:field property="password"/>
                </f:with>
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save btn btn-primary full-width" value="Créer mon profil"/>
            </fieldset>
        </form>

    </div>
</g:panel>
