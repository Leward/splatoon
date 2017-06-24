<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion des Articles</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>Articles</li>
    </ul>
</nav>

<g:panel title="Liste des article">
    <p>
        <g:link class="btn btn-primary" mapping="admin_article_create">
            Ajouter une article
        </g:link>
    </p>

    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Title</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${articleList}" var="${article}">
            <tr>
                <td>
                    <g:link mapping="admin_article_show" id="${article.id}">
                        ${article.id}
                    </g:link>
                </td>
                <td>
                    <g:link mapping="admin_article_show" id="${article.id}">
                        ${article.title}
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:panel>

</body>
</html>