package splatoon

import grails.transaction.Transactional

class TeamController {

    def index() {
        [teams: Team.findAll()]
    }

    def show(Team team) {
        [team: team]
    }

    @Transactional
    def create(Team team) {
        if(request.isPost() && team.validate()) {
            team.save()
            redirect(action: 'show', id: team.id)
        }
        [team: team]
    }

    @Transactional
    def edit(Team team) {
        if(request.method in ['POST', 'PUT'] && team.validate()) {
            team.save()
            redirect(action: 'show', id: team.id)
        }
        [team: team]
    }

    @Transactional
    def delete(Team team) {
        if(request.method in ['POST', 'DELETE']) {
            team.delete()
        }
        redirect(action: 'index')
    }

}
