package splatoon

import org.apache.commons.validator.routines.UrlValidator
import org.springframework.web.multipart.MultipartFile

class Cover {

    String name
    String url
    MultipartFile file

    static transients = ['file']

    static constraints = {
        name blank: false, unique: true
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

    void setUrl(String url) {
        this.url = URI.create(url.replace(" ", "%20")).toASCIIString()
    }

    @Override
    String toString() {
        return name
    }
}
