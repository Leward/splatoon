package splatoon

import grails.converters.JSON
import grails.plugin.awssdk.s3.AmazonS3Service
import org.springframework.web.multipart.MultipartFile

class UploadController {

    AmazonS3Service amazonS3Service

    static final TWO_MEGABYTES = 2 * 1024 * 1024

    def index() {
        MultipartFile file = request.getFile('upload')
        if (file.size > TWO_MEGABYTES) {
            def responseData = [uploaded: 0, error: [message: 'The file is too big.']]
            render responseData as JSON
        }
        if (file.empty) {
            def responseData = [uploaded: 0, error: [message: 'The file is empty.']]
            render responseData as JSON
        }

        def s3FileName = UUID.randomUUID().toString() + '-' + file.getOriginalFilename()
        def url = amazonS3Service.storeMultipartFile('splatoon', s3FileName, file)
        def responseData = [uploaded: 1, fileName: s3FileName, url: url]
        render responseData as JSON
    }
}
