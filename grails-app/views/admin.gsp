<%@ page import="splatoon.TournamentOrganizer; splatoon.TournamentEvent" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Admin Splatoon France</title>
</head>

<body>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_TO">
    <g:panel title="Tournois">
        <ul>
            <li><g:link controller="tournamentOrganizer" action="index">Organisateurs de tournois</g:link></li>
            <li><g:link controller="tournament" action="index">Liste des tournois</g:link></li>
            <li><g:link controller="tournamentEvent" action="index">Liste des événements</g:link></li>
        </ul>
    </g:panel>

    <g:panel title="Ladder">
        <ul>
            <li><g:link mapping="admin_ladder">Gestion du Ladder</g:link></li>
            <li><g:link mapping="admin_team">Équipes</g:link></li>
        </ul>
    </g:panel>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN">
    <g:panel title="Utilisateurs">
        <ul>
            <li><g:link controller="user" action="index">Utilisateurs</g:link></li>
            <li>Groupes</li>
            <li>Rôles</li>
        </ul>
    </g:panel>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_EDITOR">
    <g:panel title=" News & Mag">
        <ul>
            <li><g:link mapping="admin_news">News</g:link></li>
            <li><g:link mapping="admin_article">Articles</g:link></li>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
                <li><g:link controller="articleCategory">Catégories d'Article</g:link></li>
            </sec:ifAnyGranted>
            <li><g:link mapping="admin_cover_list">Covers</g:link></li>
        </ul>
    </g:panel>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN">
    <g:panel title="Analytics">
        <g:if test="${analyticsEnabled}">
            <h3>Utilisateurs</h3>

            <div id="chart-1-container"></div>

            <h3>Pays des visiteurs sur le dernier mois <small>(top 10, glissant)</small></h3>

            <div id="chart-2-container"></div>
        </g:if>
        <g:else>
            Analytics est desactive.
        </g:else>
    </g:panel>
</sec:ifAnyGranted>

%{-- https://ga-dev-tools.appspot.com/query-explorer/ can be used to build Google analytics queries --}%

<g:if test="${analyticsEnabled}">
    <script>
        (function (w, d, s, g, js, fs) {
            g = w.gapi || (w.gapi = {});
            g.analytics = {
                q: [], ready: function (f) {
                    this.q.push(f);
                }
            };
            js = d.createElement(s);
            fs = d.getElementsByTagName(s)[0];
            js.src = 'https://apis.google.com/js/platform.js';
            fs.parentNode.insertBefore(js, fs);
            js.onload = function () {
                g.load('analytics');
            };
        }(window, document, 'script'));
    </script>

    <script>

        gapi.analytics.ready(function () {

            /**
             * Authorize the user with an access token obtained server side.
             */
            gapi.analytics.auth.authorize({
                'serverAuth': {
                    'access_token': '${accessToken}'
                }
            });


            /**
             * Creates a new DataChart instance showing sessions over the past 30 days.
             * It will be rendered inside an element with the id "chart-1-container".
             */
            var dataChart1 = new gapi.analytics.googleCharts.DataChart({
                query: {
                    'ids': 'ga:155547152', // <-- Replace with the ids value for your view.
                    'start-date': '30daysAgo',
                    'end-date': 'yesterday',
                    'metrics': 'ga:sessions,ga:users',
                    'dimensions': 'ga:date'
                },
                chart: {
                    'container': 'chart-1-container',
                    'type': 'LINE',
                    'options': {
                        'width': '100%'
                    }
                }
            });
            dataChart1.execute();


            /**
             * Creates a new DataChart instance showing top 5 most popular demos/tools
             * amongst returning users only.
             * It will be rendered inside an element with the id "chart-3-container".
             */
            var dataChart2 = new gapi.analytics.googleCharts.DataChart({
                query: {
                    'ids': 'ga:155547152', // <-- Replace with the ids value for your view.
                    'start-date': '30daysAgo',
                    'end-date': 'yesterday',
                    'metrics': 'ga:users,ga:sessions,ga:pageviews',
                    'dimensions': 'ga:country',
                    'sort': '-ga:users',
                    'max-results': 10
                },
                chart: {
                    'container': 'chart-2-container',
                    'type': 'TABLE',
                    'options': {
                        'width': '100%',
                        'pieHole': 4 / 9,
                    }
                }
            });
            dataChart2.execute();

        });
    </script>
</g:if>

</body>
</html>
