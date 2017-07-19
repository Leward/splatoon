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
        <footer>
            <div class="share">
                <g:link url="https://www.facebook.com/sharer/sharer.php?u=${createLink(absolute: true, mapping: 'article_show', id: article.id)}" target="_blank" class="btn">
                    <i class="fa fa-facebook"></i> Partager sur Facebook</g:link>
                <g:link url="https://twitter.com/home?status=${article.title} : ${createLink(absolute: true, mapping: 'article_show', id: article.id)}"
                        target="_blank" class="btn">
                    <i class="fa fa-twitter"></i> Partager sur Twitter</a>
                </g:link>
            </div>
        </footer>
    </div>
</div>

</body>
</html>