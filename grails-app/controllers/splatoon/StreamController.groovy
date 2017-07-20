package splatoon

import org.springframework.beans.factory.annotation.Autowired
import splatoon.website.TwitchService

class StreamController {

    @Autowired
    TwitchService twitchService

    def index() {
        def allStreams = twitchService.getLiveChannels()
//        def perGameStreams = allStreams.groupBy {it.game.name()}
        render(view: 'index', model: [streams: allStreams])
    }

}
