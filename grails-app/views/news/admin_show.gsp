<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gerer une news: ${news.title}</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_news">News</g:link></li>
        <li>${news.title}</li>
    </ul>
</nav>

<g:form mapping="admin_news_delete" id="${news.id}" method="DELETE">
    <fieldset class="buttons">
        <g:link class="btn btn-primary" mapping="admin_news_edit" id="${news.id}">Modifier</g:link>
        <button type="submit" class="delete btn btn-danger" type="submit"
                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
            Supprimer
        </button>
    </fieldset>
</g:form>

<g:panel title="Gerer une news: ${news.title}">
    <div class="news">
        <g:html code="${news.content}"/>
    </div>
</g:panel>

</body>
</html>