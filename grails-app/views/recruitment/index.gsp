<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Recrutement</title>
</head>

<body>

<div class="card">
    <header class="blue">
        <h2>Poster une annonce</h2>
    </header>
    <main>
        <p><g:link mapping="recruitment_new_team_search">Je cherche une team</g:link></p>

        <p><g:link mapping="recruitment_new_teammate_search">Je recrute pour une team</g:link></p>
    </main>
</div>

<div class="row">
    <div class="col-sm-6">
        <div class="card">
            <header class="red"><h2>Ils recherchent une team</h2></header>
            <main>
                <g:each in="${searchTeamAds}" var="ad">
                    <p><g:link mapping="recruitment_show_ad" id="${ad.id}">${ad.title}</g:link></p>
                </g:each>
            </main>
        </div>
    </div>

    <div class="col-sm-6">
        <div class="card">
            <header class="red"><h2>Teams qui recrutent</h2></header>
            <main>
                <g:each in="${searchTeammateAds}" var="ad">
                    <p><g:link mapping="recruitment_show_ad" id="${ad.id}">${ad.title}</g:link></p>
                </g:each>
            </main>
        </div>
    </div>
</div>

</body>
</html>