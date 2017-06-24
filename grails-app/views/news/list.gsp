<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>News</title>
</head>

<body>

<div class="news-page">

    <g:panel title="News">
        <ul class="row equal news-list">
            <g:each in="${newsList}" var="news">
                <li class="col-sm-4 col-xs-6">
                    <div class="news">
                        <div class="news-image">
                            <g:link mapping="news_show" id="${news.id}">
                                <img src="${news.cover.url}" alt="${news.cover.name}" class="img-responsive"/>
                            </g:link>
                        </div>

                        <div class="information">
                            le <g:formatDate date="${news.dateAsDateType}" format="dd.MM.yyyy"/>
                        </div>

                        <div class="title">
                            <g:link mapping="news_show" id="${news.id}">
                                ${news.title}
                            </g:link>
                        </div>
                    </div>
                </li>
            </g:each>
        </ul>

        <div>
            Pages :
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <g:each in="${1..(nbPages)}" var="i">
                        <li class="${(i == page) ? 'active' : ''}">
                           <g:link mapping="news" params="${[page: i]}">
                               ${i}
                           </g:link>
                        </li>
                    </g:each>
                </ul>
            </nav>
        </div>
    </g:panel>

</div>

</body>
</html>