<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion des Covers</title>
</head>

<body>

<div class="cover-page">

    <nav>
        <ul class="breadcrumb">
            <li><g:link mapping="admin">Administration</g:link></li>
            <li><g:link mapping="admin_cover_list">Gestion des Covers</g:link></li>
            <li>Modfier</li>
        </ul>
    </nav>

    <div class="card">
        <header><h2>Modifier une Cover</h2></header>
        <main>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.cover}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.cover}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:uploadForm mapping="admin_cover_update" id="${cover.id}">
                <fieldset class="form">
                    <f:with bean="cover">
                        <f:field property="name"/>
                        <f:field property="file"/>
                        <div class="alert alert-info">Les images sont automatiquement redimensionnees a la taille 310x191</div>
                    </f:with>
                    <div>
                        <label>Image actuelle</label>
                        <img src="${cover.url}" alt="${cover.name} Cover" style="max-height: 200px;">
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save"
                                    value="Modifier"/>
                </fieldset>
            </g:uploadForm>
        </main>
    </div>

</div>

</body>
</html>