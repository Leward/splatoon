package splatoon.website

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class SplatoonTagLibTest extends Specification implements TagLibUnitTest<SplatoonTagLib> {

    void "should keep iframes from youtube"() {
        setup:
        def html = '<iframe width="560" height="315" src="https://www.youtube.com/embed/qnDKd-50Nkc" frameborder="0" allowfullscreen></iframe>'
        def expected = '<iframe width="560" height="315" src="https://www.youtube.com/embed/qnDKd-50Nkc" frameborder="0" allowfullscreen="allowfullscreen"></iframe>'
        expect:
        tagLib.html(code: html).toString().trim() == expected
    }

    void "should keep iframes from twitch"() {
        setup:
        def html = '<iframe  height="400" src="https://player.twitch.tv/?video=v156860792&autoplay=false"></iframe>'
        def expected = '<iframe height="400" src="https://player.twitch.tv/?video&#61;v156860792&amp;autoplay&#61;false"></iframe>'
        expect:
        tagLib.html(code: html).toString().trim() == expected
    }

    void "should keep links"() {
        setup:
        def html = '<a href="https://player.twitch.tv/?video=v156860792&autoplay=false">Test</a>'
        def expected = '<a href="https://player.twitch.tv/?video&#61;v156860792&amp;autoplay&#61;false" rel="nofollow">Test</a>'
        expect:
        tagLib.html(code: html).toString().trim() == expected
    }


}
