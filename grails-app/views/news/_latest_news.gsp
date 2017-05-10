<%@ page import="splatoon.News" %>
<div class="latest-news">
    <ul class="row">
        <g:each in="${News.findAll([max: 3, sort: 'date', order: 'desc'])}" var="news">
            <li class="col-md-4">
                ${news.title}
            </li>
        </g:each>
    </ul>
</div>