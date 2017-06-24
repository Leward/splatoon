<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Article : ${article.title}</title>
</head>

<body>

<div class="article-page">
    <div class="card news">
        <header>
            <h2>${article.title}</h2>
            <small class="details">
                Publié le <g:formatDate date="${article.dateAsDateType}" format="dd/MM/yyyy"/> <br>
                Rubrique ${article.category}
            </small>
        </header>
        <main>
            <div class="content">
                <g:html code="${article.content}"/>
            </div>
        </main>
    </div>
</div>

</body>
</html>