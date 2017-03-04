package splatoon.website

import splatoon.TournamentEvent

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

    /**
     * Renders a challonge widget
     * @attr url challonge url formatted like: http://sogfr.challonge.com/SplatofGods2
     */
    def challonge = { attrs, body ->
        if(attrs.url != null && !attrs.url.isEmpty()) {
            out << """
            <iframe src="${attrs.url}/module"
            width="100%"
            height="500"
            frameborder="0"
            scrolling="auto"
            allowtransparency="true">
            </iframe>
            """
        }
    }

    /**
     * Renders the stream of a {@link splatoon.TournamentEvent}
     * @attr event tournament event
     */
    def stream = { attrs, body ->
        def event = (TournamentEvent) attrs.event
        if(event.isTwitchStream()) {
            out << """
            <iframe 
                src="https://player.twitch.tv/?channel=pokemonvgc_eu" 
                frameborder="0" 
                allowfullscreen="true" 
                scrolling="no" 
                height="378" 
                width="620"></iframe>
            """
        } else if(event.isYoutubeGamingStream()) {
            out << """
            <iframe
            width="1084"
            height="610"
            src="https://gaming.youtube.com/embed/${event.extractYoutubeStreamId()}"
            frameborder="0" allowfullscreen>
            """
        }

    }
}
