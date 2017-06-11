package splatoon

import grails.validation.Validateable
import org.springframework.validation.Errors

/**
 * Request for a new password when password has been forgotten.
 */
class NewPasswordRequest implements Validateable {

    String email

    static constraints = {
        email(blank: false, validator: { val, NewPasswordRequest obj, Errors errors ->
            if(!User.findByEmail(val)) {
                errors.rejectValue("email","user_not_found", "Aucun compte ne correspond à cette adresse e-mail")
            }
        })
    }

}
