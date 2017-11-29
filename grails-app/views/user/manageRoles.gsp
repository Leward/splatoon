<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gérer les roles d'un utilisateurs</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link controller="user" action="index">Utilisateurs</g:link></li>
        <li>Gérer les rôles</li>
    </ul>
</nav>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:hasErrors bean="${this.tournamentOrganizer}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.tournamentOrganizer}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                    error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:panel title="Gérer les roles">
    <h3>${user.username}</h3>

    <div class="alert alert-info">
        Le role TO est attribue automatiquement si l'utilisateur <g:link mapping="admin_tournamentOrganizer_list">est gerant d'un TO</g:link>.
    </div>

    <g:form mapping="admin_user_uroles" id="${user.id}">
        <table>
            <thead>
            <tr>
                <th>Role</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${roles}" var="role">
                <tr>
                    <td>
                        <label for="role_${role.authority}">${role.authority}</label>
                    </td>
                    <td>
                        <g:checkBox
                                id="role_${role.authority}"
                                name="roles[]"
                                value="${role.authority}"
                                checked="${user.hasRole(role.authority)}" />
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <g:submitButton name="submit" value="Enregsitrer" />
    </g:form>
</g:panel>

%{--<div class="panel">--}%
%{--<div class="panel-header">--}%
%{--<h2>Ajouter un organisateur de tournoi</h2>--}%
%{--</div>--}%

%{--<div class="panel-body padded">--}%
%{--<g:form action="save">--}%
%{--<fieldset class="form">--}%
%{--<f:all bean="tournamentOrganizer"/>--}%
%{--</fieldset>--}%
%{--<fieldset class="buttons">--}%
%{--<g:submitButton name="create" class="save"--}%
%{--value="${message(code: 'default.button.create.label', default: 'Create')}"/>--}%
%{--</fieldset>--}%
%{--</g:form>--}%
%{--</div>--}%
%{--</div>--}%

</body>
</html>
