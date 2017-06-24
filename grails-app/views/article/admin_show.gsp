<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gerer une article: ${article.title}</title>
</head>

<body>

<nav>
    <ul class="breadcrumb">
        <li><g:link mapping="admin">Administration</g:link></li>
        <li><g:link mapping="admin_article">Articles</g:link></li>
        <li>${article.title}</li>
    </ul>
</nav>

<g:form mapping="admin_article_delete" id="${article.id}" method="DELETE">
    <fieldset class="buttons">
        <g:link class="btn btn-primary" mapping="admin_article_edit" id="${article.id}">Modifier</g:link>
        <button type="submit" class="delete btn btn-danger" type="submit"
                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
            Supprimer
        </button>
    </fieldset>
</g:form>

<g:panel title="Gerer une article: ${article.title}">
    <p>Catégorie: ${article.category}</p>
    <div class="article">
        <g:html code="${article.content}"/>
    </div>
</g:panel>

</body>
</html>