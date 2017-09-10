package splatoon.website

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.owasp.html.HtmlPolicyBuilder
import org.owasp.html.PolicyFactory
import org.owasp.html.Sanitizers
import splatoon.TournamentEvent

import java.util.regex.Pattern

class SplatoonTagLib {

    private static final Pattern HTML_CLASS = Pattern.compile("[a-zA-Z0-9\\s,\\-_]+");
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    /**
     * Renders the content in a panel
     *
     * @attr title title of the panel
     * @attr padded whether the body should have a lightly padding or not
     * @attr class supplementary classes to add (string)
     */
    def panel = { attrs, body ->
        out << """<div class="card ${attrs.class}">"""
        if (attrs.title != null && !attrs.title.isEmpty()) {
            out << """<header>
            <h3>${attrs.title}</h3>
            </header>"""
        }
        out << """
        <main>
            ${body()}
        </main>
        """
        out << '</div>'
    }

    /**
     * Renders a challonge widget
     * @attr url challonge url formatted like: http://sogfr.challonge.com/SplatofGods2
     */
    def challonge = { attrs, body ->
        if (attrs.url != null && !attrs.url.isEmpty()) {
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
        if (event.isTwitchStream()) {
            out << """
            <iframe 
                src="https://player.twitch.tv/?channel=${event.extractTwitchStreamId()}" 
                frameborder="0" 
                allowfullscreen="true" 
                scrolling="no" 
                height="378" 
                width="620"></iframe>
            """
        } else if (event.isYoutubeGamingStream()) {
            out << """
            <iframe
            width="1084"
            height="610"
            src="https://gaming.youtube.com/embed/${event.extractYoutubeStreamId()}"
            frameborder="0" allowfullscreen></iframe>
            """
        }

    }

    /**
     * Print safe HTML code
     * @attr code HTML Code to be sanitized then printed
     */
    def html = { attrs, body ->
        def policy = Sanitizers.BLOCKS
                .and(Sanitizers.FORMATTING)
                .and(Sanitizers.LINKS)
                .and(Sanitizers.IMAGES)
                .and(new HtmlPolicyBuilder()
                .allowStyling()
                .allowAttributes("class").matching(HTML_CLASS).globally()
                .toFactory())
                .and(new HtmlPolicyBuilder()
                .allowStandardUrlProtocols()
                .allowAttributes("width", "height", "frameborder", "webkitallowfullscreen", "mozallowfullscreen", "allowfullscreen").onElements("iframe")
                .allowAttributes("src").matching(Pattern.compile("^((https:)?//player\\.vimeo\\.com/|(https:)?//www\\.youtube\\.com/|(https:)?//player\\.twitch\\.tv/).+"))
                .onElements("iframe")
                .allowElements("iframe", 'hr')
                .toFactory())
        out << policy.sanitize(attrs.code)
    }

    /**
     * Print safe HTML code for an
     * @attr code HTML Code to be sanitized then printed
     * @attr strict if true does not allow formatting, blocks, images, etc.
     */
    def excerptHtml = { attrs, body ->
        PolicyFactory policy
        if (attrs.strict || attrs.strict == 'true') {
            policy = Sanitizers.FORMATTING
                    .and(new HtmlPolicyBuilder()
                    .toFactory())
        } else {
            policy = Sanitizers.BLOCKS
                    .and(Sanitizers.FORMATTING)
                    .and(Sanitizers.LINKS)
                    .and(new HtmlPolicyBuilder()
                    .toFactory())
        }
        out << policy.sanitize(attrs.code)
    }

    def enforceResponsiveContent = { attrs, body ->
        String originalHtml = body().toString()
        Document document = Jsoup.parseBodyFragment(originalHtml);
        document.select("img").addClass("img-responsive")
        out << document.body().html()
    }
}
