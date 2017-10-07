<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <title>Webapp Info</title>
</head>

<body>
<section>
    <h2>Application Status</h2>
    <ul class="dropdown-menu">
        <li>Environment: ${grails.util.Environment.current.name}</li>
        <li>App profile: ${grailsApplication.config.grails?.profile}</li>
        <li>App version: <g:meta name="info.app.version"/></li>
        <li>Grails version:<g:meta name="info.app.grailsVersion"/></li>
        <li>Groovy version: ${GroovySystem.getVersion()}</li>
        <li>JVM version: ${System.getProperty('java.version')}</li>
        <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
    </ul>
</section>

<section>
    <h2>Available Controllers:</h2>
    <ul>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
            <li class="controller">
                <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
            </li>
        </g:each>
    </ul>
</div>
</section>

<section>
    <h2>Artefacts</h2>
    <ul>
        <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
        <li>Domains: ${grailsApplication.domainClasses.size()}</li>
        <li>Services: ${grailsApplication.serviceClasses.size()}</li>
        <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
    </ul>
</section>

<section>
    <h2>Installed Plugins</h2>
    <ul>
        <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
            <li>${plugin.name} - ${plugin.version}</li>
        </g:each>
    </ul>
</section>


%{--<section>--}%
    %{--<h2>Headers</h2>--}%
    %{--<ul>--}%
        %{--<g:each var="headerName" in="${request.getHeaderNames()}">--}%
            %{--<li>${headerName}: ${request.getHeader(headerName)}</li>--}%
        %{--</g:each>--}%
    %{--</ul>--}%
%{--</section>--}%

</body>
</html>
