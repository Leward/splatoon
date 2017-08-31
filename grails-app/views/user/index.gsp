<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Users</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Utilisateurs</li>
    </ul>
</nav>

<div>
    <g:panel title="Utilisateurs">

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <table>
            <thead>
            <tr>
                <th>#</th>
                <g:sortableColumn property="username" title="Username" />
                <th>Email</th>
                <th>Roles</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.roles.collect { it.authority }.join(',')}</td>
                    <td>
                        <g:form method="POST" action="delete" id="${user.id}">
                            <g:link class="btn btn-default" mapping="admin_user_uroles"
                                    id="${user.id}">Gérer rôles</g:link>
                            <button type="submit" class="btn btn-danger"
                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur? Cette action est irréversible. ');">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </button>
                        </g:form>
                    </td>
                </tr>
            </g:each>

            </tbody>
        </table>
    </g:panel>
</div>

</body>
</html>
