<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>News : ${news.title}</title>
</head>

<body>

<div class="row" class="news-page">
    <div class="col-xs-12" class="news">
        <h2>${news.title}</h2>
        <div class="details">
            Poste le <g:formatDate date="${news.dateAsDateType}" format="dd/MM/yyyy"/>
        </div>
        <g:html code="${news.content}" />
    </div>
</div>

</body>
</html>