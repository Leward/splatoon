<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion des Covers</title>
</head>

<body>

<div class="ladder-page">

    <nav>
        <ul class="breadcrumb">
            <li><g:link mapping="admin">Administration</g:link></li>
            <li>Gestion des Covers</li>
        </ul>
    </nav>

    <div class="card">
        <header><h2>Gestion des Covers</h2></header>
        <main>
            <g:if test="${flash.message}">
                <div class="alert alert-info">${flash.message}</div>
            </g:if>
            <div class="buttons">
                <g:link mapping="admin_cover_create" class="btn btn-primary">Ajouter</g:link>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${covers}" var="cover">
                    <tr>
                        <th>
                            ${cover.name}
                        </th>
                        <td>
                            <img src="${cover.url}" alt="${cover.name} Cover" style="max-height: 200px;">
                        </td>
                        <td>
                            <g:form mapping="admin_cover_delete" id="${cover.id}">
                                <g:link mapping="admin_cover_update" id="${cover.id}"
                                        class="btn btn-default">Modifier</g:link>
                                <button type="submit" class="btn btn-danger"
                                        onclick="return confirm('Êtes-vous sûr?');">
                                    Supprimer
                                </button>
                            </g:form>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </main>
    </div>

</div>

</body>
</html>