package splatoon

import grails.plugin.springsecurity.annotation.Secured
import  grails.gorm.transactions.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_TO'])
class TeamController {

    def index() {
        [teams: Team.list(params)]
    }

    def show(Team team) {
        [team: team]
    }

    @Transactional
    def create() {
        def team = new Team(params)
        if(request.isPost() && team.validate()) {
            team.save()
            redirect(action: 'show', id: team.id)
        }
        render(view: 'create', model: [team: team])
    }

    @Transactional
    def edit(Team team) {
        if(request.method in ['POST', 'PUT'] && team.validate()) {
            team.save()
            redirect(action: 'show', id: team.id)
        }
        render(view: 'edit', model: [team: team])
    }

    @Transactional
    def delete(Team team) {
        if(request.method in ['POST', 'DELETE']) {
            team.delete()
        }
        redirect(action: 'index')
    }

}
