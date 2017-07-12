<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Users</title>
</head>

<body>

<div>
    <g:panel title="Inscription">
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Username</th>
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
                        <g:link class="btn" mapping="admin_user_uroles" id="${user.id}">Gérer rôles</g:link>
                    </td>
                </tr>
            </g:each>

            </tbody>
        </table>
    </g:panel>
</div>

</body>
</html>
