package splatoon

import grails.plugin.springsecurity.annotation.Secured
import  grails.gorm.transactions.Transactional

class TeamController {

    LadderService ladderService

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def index() {
        [teams: Team.list(params)]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    def showAdmin(Team team) {
        [team: team]
    }

    def show(Team team) {
        [ team: team,
          rankings: ladderService.getRankings(),
          results: Ladder.findAllByTeam(team, [sort: 'date', order: 'desc']) ]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    @Transactional
    def create() {
        def team = new Team(params)
        if(request.isPost() && team.validate()) {
            team.save()
            redirect(action: 'showAdmin', id: team.id)
        }
        render(view: 'create', model: [team: team])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    @Transactional
    def edit(Team team) {
        if(request.method in ['POST', 'PUT'] && team.validate()) {
            team.save()
            redirect(action: 'showAdmin', id: team.id)
        }
        render(view: 'edit', model: [team: team])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_TO'])
    @Transactional
    def delete(Team team) {
        if(request.method in ['POST', 'DELETE']) {
            team.delete()
        }
        redirect(action: 'index')
    }

}
