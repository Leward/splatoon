<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion des News</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li>News</li>
    </ul>
</nav>

<g:panel title="Liste des news">
    <p>
        <g:link class="btn btn-primary" mapping="admin_news_create">
            Ajouter une news
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
        <g:each in="${newsList}" var="${news}">
            <tr>
                <td>
                    <g:link mapping="admin_news_show" id="${news.id}">
                        ${news.id}
                    </g:link>
                </td>
                <td>
                    <g:link mapping="admin_news_show" id="${news.id}">
                        ${news.title}
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:panel>

</body>
</html>