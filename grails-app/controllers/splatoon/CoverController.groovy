package splatoon

import grails.gorm.transactions.Transactional
import grails.plugin.awssdk.s3.AmazonS3Service
import grails.plugin.springsecurity.annotation.Secured
import net.coobird.thumbnailator.Thumbnails

@Secured([Role.ROLE_ADMIN, Role.ROLE_EDITOR])
class CoverController {

    AmazonS3Service amazonS3Service

    def index() {
        render(view: 'index', model: [covers: Cover.list()])
    }

    @Transactional
    def create() {
        def cover = new Cover(params)
        cover.file = params.file
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
        cover.file = params.file
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
            cover.delete(flush: true)
            flash.message = 'Cover supprimée'
            redirect(action: 'index')
        }
    }

    private void uploadFileIfPresent(Cover cover) {
        if(cover.file != null && !cover.file.empty) {
            def s3FileName = UUID.randomUUID().toString() + '-' + cover.file.getOriginalFilename()
            def resizedImage = Thumbnails.of(cover.file.inputStream)
                .size(310, 191)
                .keepAspectRatio(false)
                .asFiles([File.createTempFile('splatoon-', s3FileName)])[0]
            def url = amazonS3Service.storeFile('splatoon', s3FileName, resizedImage)
            cover.url = url
        }
    }
}
