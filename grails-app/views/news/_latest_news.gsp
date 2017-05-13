<%@ page import="splatoon.News" %>
<div class="latest-news">
    <ul class="row equal">
        <g:each in="${News.findAll([max: 4, sort: 'date', order: 'desc'])}" var="news">
            <li class="col-md-3 col-sm-6 col-xs-12">
                <div class="news">
                    <div class="news-image">
                        <g:link mapping="news_show" id="${news.id}">
                            <img src="https://s3-eu-central-1.amazonaws.com/splatoon/0be69296-2b43-4c19-8d2e-b567df7f5fdb-image.png"/>
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