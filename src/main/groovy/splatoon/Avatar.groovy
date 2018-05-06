package splatoon

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class Avatar implements Validateable {

    MultipartFile file

    static constraints = {
        file nullable: false, validator: { val, obj ->
            if (val == null || val.empty) {
                return false
            }
            return ['jpeg', 'jpg', 'png'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }

    String getExtension() {
        if (!file || !file.originalFilename.contains('.')) {
            return ''
        }
        return file.originalFilename.split('\\.')[-1]
    }

    def propertyMissing(name) {
        null
    }

}
