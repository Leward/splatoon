package splatoon

import org.springframework.beans.factory.annotation.Autowired
import splatoon.website.TwitchService

class HomeController {

    @Autowired
    TwitchService twitchService

    def index() {
        def viewModel = [
                streams: twitchService.getLiveChannels()
        ]
        render(view: '/index', model: viewModel)
    }
}
