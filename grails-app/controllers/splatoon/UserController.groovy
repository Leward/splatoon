package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

class UserController {

    SpringSecurityService springSecurityService
    BCryptPasswordEncoder passwordEncoder
    UserService userService

    @Transactional
    def register() {
        if (request.method == "POST") {
            def user = new User(params)
            if(user.validate()) {
                user.save()
                redirect(mapping: "registration_success")
            }
            return [newUser: user]
        }
        return [newUser: new User()]
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    def myAccount() {
        User me = springSecurityService.currentUser as User
        render(view: 'my_account', model: [me: me])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def changePassword(NewPassword newPassword) {
        if(request.isPost() && !newPassword.hasErrors() && validateCurrentPassword(newPassword)) {
            def user = User.get((springSecurityService.currentUser as User).id)
            user.setPassword(newPassword.newPassword)
            if(!user.isDirty('password')) { // Yep that's a hack there are some issues with dirty checking
                user.encodePassword()
            }
            user.save()
            flash.message = 'Mot de passe mis à jour.'
            redirect(mapping: 'my_account')
        }
        render(view: 'change_password', model: [newPassword: newPassword])
    }

    private boolean isCurrentPasswordCorrect(NewPassword newPassword) {
        def user = springSecurityService.currentUser as User
        return passwordEncoder.isPasswordValid(user.password, newPassword.oldPassword, null)
    }

    private boolean validateCurrentPassword(NewPassword newPassword) {
        if(!isCurrentPasswordCorrect(newPassword)) {
            newPassword.errors.rejectValue('oldPassword', 'wrong_password', 'Current password is incorrect')
            return false
        }
        return true
    }

    def confirmAccount() {

    }

    def forgottenPassword(NewPasswordRequest newPasswordRequest) {
        if(request.isPost() && !newPasswordRequest.hasErrors()) {
            userService.resetUserPassword(newPasswordRequest.email)
            render(view: 'forgottenPassword_sent')
        }
        else {
            render(view: 'forgottenPassword', model: [newPasswordRequest: newPasswordRequest])
        }
    }

    def index() {
        [users: User.getAll()]
    }
}
