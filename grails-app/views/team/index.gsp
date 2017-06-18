<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion des équipes</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Gestion des équipes</li>
    </ul>
</nav>

<div class="card">
    <header><h2>Gestion des équipes</h2></header>
    <main>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div class="buttons">
            <g:link class="btn btn-primary" action="create">Nouvelle équipe</g:link>
        </div>
        <f:table collection="${teamList}"/>
    </main>
</div>

</body>
</html>