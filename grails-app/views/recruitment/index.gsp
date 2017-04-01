<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Recrutement</title>
</head>

<body>

<g:panel title="Poster une annonce">
    <p><g:link mapping="recruitment_new_team_search">Je cherche une team</g:link></p>

    <p><g:link mapping="recruitment_new_teammate_search">Je recrute pour une team</g:link></p>
</g:panel>

<div class="row">
    <g:panel title="Ils recherche une team" class="col-sm-6">
        <g:each in="${searchTeamAds}" var="ad">
            <p><g:link mapping="recruitment_show_ad" id="${ad.id}">${ad.title}</g:link></p>
        </g:each>
    </g:panel>

    <g:panel title="Teams qui recrutent" class="col-sm-6">
        <g:each in="${searchTeammateAds}" var="ad">
            <p><g:link mapping="recruitment_show_ad" id="${ad.id}">${ad.title}</g:link></p>
        </g:each>
    </g:panel>
</div>

</body>
</html>