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
            <g:html code="${news.content}"/>
            </div>
        </main>
    </div>
</div>

</body>
</html>