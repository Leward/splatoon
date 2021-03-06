<%@ page import="splatoon.News" %>
<div class="latest-news">
    <ul class="row equal">
        <g:each in="${News.findAll([max: 4, sort: 'date', order: 'desc'])}" var="news">
            <li class="col-sm-3 col-xs-6">
                <div class="news">
                    <div class="news-image">
                        <g:link mapping="news_show" id="${news.id}">
                            <img src="${news.cover.url}" alt="${news.cover.name}"/>
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
</div>