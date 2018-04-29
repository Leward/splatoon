package splatoon

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired
import splatoon.website.TwitchService

class HomeController {

    @Autowired
    TwitchService twitchService

    def index() {
        def usersLookingForATeam = PlayerProfile.findAllByLookingForATeam(true, [
                max: 6,
                sort: 'updatedAt',
                order: 'desc'
        ]).collect { it.user }
        def viewModel = [
                streams: twitchService.getTopLiveChannels(8),
                liveEvent: TournamentEvent.getLiveEvent(),
                usersLookingForATeam: usersLookingForATeam
        ]
        render(view: '/index', model: viewModel)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO', 'ROLE_EDITOR'])
    def admin() {
        render(view: '/admin')
    }
}
