package splatoon

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired
import splatoon.website.TwitchService

class HomeController {

    @Autowired
    TwitchService twitchService

    def index() {
        def viewModel = [
                streams: twitchService.getTopLiveChannels(8)
        ]
        render(view: '/index', model: viewModel)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO', 'ROLE_EDITOR'])
    def admin() {
        render(view: '/admin')
    }
}
