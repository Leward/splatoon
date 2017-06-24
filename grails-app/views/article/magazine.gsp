<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>InkZone Magazine</title>
</head>

<body>

<g:each in="${categories}" var="categoryEntry">
    <g:panel title="${categoryEntry.key.name}">
        <ul class="articles row equal">
        <g:each in="${categoryEntry.value}" var="${article}">
            <li class="col-sm-4 col-xs-6">
                <div class="article">
                    <div class="article-image">
                        <g:link mapping="article_show" id="${article.id}">
                            <img src="${article.cover.url}" alt="${article.cover.name}" class="img-responsive"/>
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
    </g:panel>
</g:each>

</body>
</html>