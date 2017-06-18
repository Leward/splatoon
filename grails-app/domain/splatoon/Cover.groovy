package splatoon

import org.grails.validation.routines.UrlValidator
import org.springframework.web.multipart.MultipartFile

class Cover {

    String name
    String url
    MultipartFile file

    static transients = ['file']

    static constraints = {
        name blank: false
        url nullable: true, validator: { val, obj ->
            return obj.file != null || (val != null && UrlValidator.getInstance().isValid(val))
        }
        file nullable: true, validator: { val, obj ->
            if((val == null || val.empty) && obj.url != null) {
                return true
            }
            if (val != null && !val.empty) {
                return ['jpeg', 'jpg', 'png'].any { extension ->
                    val.originalFilename?.toLowerCase()?.endsWith(extension)
                }
            } else {
                return false
            }
        }
    }
}
