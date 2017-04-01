<%@ page import="splatoon.AdType; splatoon.TournamentOrganizer; splatoon.TournamentEvent; splatoon.RecruitingAd" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Portail Splatoon France</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<div class="home-page">
    <div class="row">
        <div class="next-events col-sm-6 col-xs-12">
            <g:render template="tournamentEvent/nextEvents"
                      model="${[nextEvents: TournamentEvent.findUpcomingEvents(6)]}"/>
        </div>

        <div class="news col-sm-6 col-xs-12">
            <g:panel title="Les dernières nouvelles">
                <p>Aucun contenu pour le moment.</p>
            </g:panel>
        </div>
    </div>

    <div class="recruitment row">
        <g:panel title="Recrutement" class="col-xs-12">
            <div class="row">
                <g:each in="${splatoon.RecruitingAd.findAll()}" var="ad">
                    <div class="recruitment-ad col-md-3 col-sm-4 col-xs-2">
                        <h3>
                            <g:if test="${ad.type == AdType.LOOKING_FOR_TEAM_AD}">
                                Recrute
                            </g:if>
                            <g:if test="${ad.type == AdType.LOOKING_FOR_TEAMMATE_AD}">
                                Cherche
                            </g:if>
                        </h3>

                        <p>${ad.title}</p>

                        <p>
                            Par ${ad.author.username}
                            le <g:formatDate date="${ad.createdAtAsDate}" dateStyle="SHORT" />
                        </p>
                    </div>
                </g:each>
            </div>
        </g:panel>
    </div>
</div>

</body>
</html>
