<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Users</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
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
            </tr>
            </thead>
            <tbody>
            <g:each in="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                </tr>
            </g:each>

            </tbody>
        </table>
    </g:panel>
</div>

</body>
</html>
