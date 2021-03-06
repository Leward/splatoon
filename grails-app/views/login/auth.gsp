<%@ page import="splatoon.User; splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inscription</title>
</head>

<body>

<div class="login-page">

    <div class="row">
        <div class="col-md-6">
            <g:panel title="Connexion" class="login">

                <div class="width-80p">

                    <g:if test='${flash.message}'>
                        <div class="alert alert-danger">
                            <p>${flash.message}
                        </div>
                    </g:if>

                    <form action="${postUrl ?: '/login/authenticate'}" method="POST" class="login">
                        <div>
                            <label for="login_username">Nom d'utilisateur :</label>
                            <input type="text" name="${usernameParameter ?: 'username'}" id="login_username"/>
                        </div>

                        <div>
                            <label for="login_password">Mot de passe :</label>
                            <input type="password" name="${passwordParameter ?: 'password'}" id="login_password"/>
                        </div>

                        <div id="remember_me_holder">
                            <label for="remember_me">
                                <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}"
                                       id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                                Se souvenir de moi
                            </label>
                        </div>

                        <div class="buttons">
                            <div class="login">
                                <input type="submit" class="btn btn-primary full-width" value="Me connecter"/>
                            </div>

                            <div class="forgotten-password">
                                <p><g:link mapping="forgotten_password">Mot de passe oublié ?</g:link></p>
                            </div>
                        </div>
                    </form>
                </div>
            </g:panel>
        </div>

        <div class="col-md-6">
            <g:panel title="Pas encore de profil ?" class="register">
                <g:include view="user/_register_introduction.gsp"/>
                <g:include view="user/_register_form.gsp" model="${[newUser: new User()]}"/>
            </g:panel>
        </div>
    </div>

</div>

</body>
</html>
