package splatoon

import grails.plugins.mail.MailService
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

@Transactional
class UserService {

    public static final int GENERATED_PASSWORD_LENGTH = 10

    MailService mailService

    void resetUserPassword(String email) {
        def user = User.findByEmail(email)
        def newPassword = RandomStringUtils.randomAlphanumeric(GENERATED_PASSWORD_LENGTH)
        user.password = newPassword
        if(!user.isDirty('password')) {
            user.encodePassword()
        }
        user.save()
        mailService.sendMail {
            to email
            subject "Votre nouveau mot de passe InkZone"
            body "Vous avez oublié votre mot de passe InkZone ? Ce dernier a été réinitialisé. " +
                    "Vous pouvez vous connecter avec le mot de passe suivant : ${newPassword}. " +
                    "N'oubliez pas de changer votre mot passe après vous être connecté."
        }
    }

}
