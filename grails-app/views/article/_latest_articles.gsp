<%@ page import="splatoon.Article" %>
<div class="latest-news">
    <ul class="row equal">
        <g:each in="${splatoon.Article.findAll([max: 4, sort: 'date', order: 'desc'])}" var="article">
            <li class="col-sm-3 col-xs-6">
                <div class="news">
                    <div class="news-image">
                        <g:link mapping="article_show" id="${article.id}">
                            <img src="${article.cover.url}" alt="${article.cover.name}"/>
                        </g:link>
                    </div>

                    <div class="information">
                        le <g:formatDate date="${article.dateAsDateType}" format="dd.MM.yyyy"/>
                    </div>

                    <div class="title">
                        <g:link mapping="article_show" id="${article.id}">
                            ${article.title}
                        </g:link>
                    </div>
                </div>
            </li>
        </g:each>
    </ul>
</div>