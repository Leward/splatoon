<textarea
        id="${property}"
        name="${property}"
        class="ckeditor-simple"
    ${(required) ? 'required' : ''}>${value}</textarea>

<g:set var="useCkEditor" value="${true}" scope="request" />