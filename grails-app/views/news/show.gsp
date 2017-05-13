<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>News : ${news.title}</title>
</head>

<body>

<div class="row news-page">
    <div class="col-xs-12 news">
        <h2>${news.title}</h2>

        <div class="details">
            Poste le <g:formatDate date="${news.dateAsDateType}" format="dd/MM/yyyy"/>
        </div>

        <div class="content">
            <g:html code="${news.content}"/>
        </div>
    </div>
</div>

</body>
</html>