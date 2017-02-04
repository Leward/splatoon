package splatoon.website

class SplatoonTagLib {
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    /**
     * Renders the content in a panel
     *
     * @attr title title of the panel
     * @attr padded whether the body should have a lightly padding or not
     * @attr class supplementary classes to add (string)
     */
    def panel = { attrs, body ->
        out << """<div class="panel ${attrs.class}">"""
        if(attrs.title != null && !attrs.title.isEmpty()) {
            out << """<div class="panel-header">
            <h3>${attrs.title}</h3>
            </div>"""
        }
        out << """
        <div class="panel-body ${(attrs.padded) ? 'padded' : ''}">
            ${body()}
        </div>
        """
        out << '</div>'
    }
}
