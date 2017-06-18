package splatoon

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired
import splatoon.website.TwitchService

class HomeController {

    @Autowired
    TwitchService twitchService

    def index() {
        def test = User.findByUsername('Ayo')
        def viewModel = [
                streams: twitchService.getLiveChannels()
        ]
        render(view: '/index', model: viewModel)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO', 'ROLE_EDITOR'])
    def admin() {
        render(view: '/admin')
    }
}
