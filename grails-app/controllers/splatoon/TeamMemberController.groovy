package splatoon

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import net.coobird.thumbnailator.Thumbnails

@Secured(['ROLE_ADMIN', 'ROLE_TO'])
class TeamMemberController {

    UploadService uploadService

    def show(TeamMember teamMember) {
        [teamMember: teamMember]
    }

    @Transactional
    def create() {
        def teamMember = new TeamMember(params)
        if(request.isPost() && teamMember.validate()) {
            uploadFileIfPresent(teamMember)
            teamMember.save()
            flash.message = "Membre ${teamMember.name} ajoute"
            redirect(controller: 'team', action: 'showAdmin', id: teamMember.team.id)
        }
        render(view: 'create', model: [teamMember: teamMember])
    }

    @Transactional
    def edit(TeamMember teamMember) {
        if(request.isPost() && teamMember.validate()) {
            uploadFileIfPresent(teamMember)
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

    private uploadFileIfPresent(TeamMember teamMember) {
        if(teamMember.file != null && !teamMember.file.empty) {
            def s3FileName = UUID.randomUUID().toString() + '-' + teamMember.file.getOriginalFilename()
            def resizedImage = Thumbnails.of(teamMember.file.inputStream)
                    .size(110, 115)
                    .keepAspectRatio(false)
                    .asFiles([File.createTempFile('splatoon-', s3FileName)])[0]
            def url = uploadService.upload(s3FileName, resizedImage)
            teamMember.avatar = url
        }
    }
}
