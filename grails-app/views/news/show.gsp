<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>News : ${news.title}</title>
</head>

<body>

<div class="news-page">
    <div class="card news">
        <header>
            <h2>${news.title}</h2>
            <small class="details">Poste le <g:formatDate date="${news.dateAsDateType}" format="dd/MM/yyyy"/></small>
        </header>
        <main>
            <div class="content">
                <g:enforceResponsiveContent>
                    <g:html code="${news.content}"/>
                </g:enforceResponsiveContent>
            </div>
        </main>
        <footer>
            <div class="share">
                <g:link url="https://www.facebook.com/sharer/sharer.php?u=${createLink(absolute: true, mapping: 'news_show', id: news.id)}" target="_blank" class="btn">
                    <i class="fa fa-facebook"></i> Partager sur Facebook</g:link>
                <g:link url="https://twitter.com/home?status=${news.title} : ${createLink(absolute: true, mapping: 'news_show', id: news.id)}"
                        target="_blank" class="btn">
                    <i class="fa fa-twitter"></i> Partager sur Twitter
                </g:link>
            </div>
        </footer>
    </div>
</div>

</body>
</html>