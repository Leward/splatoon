package splatoon

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_TO'])
class TeamMemberController {

    def show(TeamMember teamMember) {
        [teamMember: teamMember]
    }

    @Transactional
    def create() {
        def teamMember = new TeamMember(params)
        if(request.isPost() && teamMember.validate()) {
            teamMember.save()
            flash.message = "Membre ${teamMember.name} ajoute"
            redirect(controller: 'team', action: 'showAdmin', id: teamMember.team.id)
        }
        render(view: 'create', model: [teamMember: teamMember])
    }

    @Transactional
    def edit(TeamMember teamMember) {
        if(request.isPost() && teamMember.validate()) {
            teamMember.save()
            flash.message = "Membre ${teamMember.name} modifie"
            redirect(controller: 'team', action: 'showAdmin', id: teamMember.team.id)
        }
        render(view: 'edit', model: [teamMember: teamMember])
    }

    @Transactional
    def delete(TeamMember teamMember) {
        def team = teamMember.team
        if(request.method.toUpperCase() == 'DELETE') {
            teamMember.delete()
            flash.message = "Membre ${teamMember.name} supprime"
            redirect(controller: 'team', action: 'showAdmin', id: teamMember.team.id)
        }
    }
}
