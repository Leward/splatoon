<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inscription</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<div class="registration-page">

    <g:panel title="Connexion" class="login">

        <g:if test='${flash.message}'>
            <div class="alert alert-danger">
                <p>${flash.message}
            </div>
        </g:if>

        <div class="width-80p">
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
                        <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                        Se souvenir de moi
                    </label>
                </div>

                <div class="buttons">
                    <div class="login">
                        <input type="submit" class="btn btn-primary full-width" value="Me connecter"/>
                    </div>
                    <div class="forgotten-password"></div>
                </div>
            </form>
        </div>
    </g:panel>

    <g:include controller="user" action="register" />

</div>

</body>
</html>
