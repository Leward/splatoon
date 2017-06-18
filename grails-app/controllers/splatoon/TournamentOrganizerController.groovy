package splatoon

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class TournamentOrganizerController {

    static scaffold = TournamentOrganizer

}
