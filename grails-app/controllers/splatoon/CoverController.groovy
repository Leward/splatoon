package splatoon

import grails.plugin.awssdk.s3.AmazonS3Service
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Role.ROLE_ADMIN, Role.ROLE_EDITOR])
class CoverController {

    AmazonS3Service amazonS3Service

    def index() {
        render(view: 'index', model: [covers: Cover.list()])
    }

    @Transactional
    def create() {
        def cover = new Cover(params)
        if(request.isPost() && cover.validate()) {
            uploadFileIfPresent(cover)
            cover.save()
            flash.message = 'Cover ajoutée'
            redirect(action: 'index')
        }
        render(view: 'create', model: [cover: cover])
    }

    @Transactional
    def update(Cover cover) {
        cover = Cover.get(params.id)
        cover.properties = params
        if(request.isPost() && cover.validate()) {
            uploadFileIfPresent(cover)
            cover.save()
            flash.message = 'Cover mise à jour'
            redirect(action: 'index')
        }
        render(view: 'update', model: [cover: cover])
    }

    @Transactional
    def delete(Cover cover) {
        if(request.isPost()) {
            cover.delete()
            flash.message = 'Cover supprimée'
            redirect(action: 'index')
        }
    }

    private void uploadFileIfPresent(Cover cover) {
        if(cover.file != null && !cover.file.empty) {
            def s3FileName = UUID.randomUUID().toString() + '-' + cover.file.getOriginalFilename()
            def url = amazonS3Service.storeMultipartFile('splatoon', s3FileName, cover.file)
            cover.url = url
        }
    }
}