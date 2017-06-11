package splatoon

import grails.validation.Validateable
import org.springframework.validation.Errors

class NewPassword implements Validateable {

    String oldPassword
    String newPassword
    String newPasswordRepeat

    static constraints = {
        oldPassword(blank: false, password: true)
        newPassword(blank: false, password: true)
        newPasswordRepeat(blank: false, password: true, validator: { val, NewPassword obj, Errors errors ->
            if(obj.newPassword != obj.newPasswordRepeat) {
                errors.rejectValue("newPasswordRepeat","passwords_not_matching", "Les mots de passes ne correspondent pas")
            }
        })
    }

}
