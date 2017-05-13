<%@ page import="splatoon.News" %>
<div class="latest-news">
    <ul class="row">
        <g:each in="${News.findAll([max: 3, sort: 'date', order: 'desc'])}" var="news">
            <li class="col-md-4">
                <g:link mapping="news_show" id="${news.id}">
                    ${news.title}
                </g:link>
            </li>
        </g:each>
    </ul>
</div>