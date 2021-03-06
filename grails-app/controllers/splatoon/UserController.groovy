package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder
import  grails.gorm.transactions.Transactional
import net.coobird.thumbnailator.Thumbnails
import org.springframework.security.access.annotation.Secured
import org.springframework.validation.BindingResult

class UserController {

    SpringSecurityService springSecurityService
    BCryptPasswordEncoder passwordEncoder
    UserService userService
    UploadService uploadService

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

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def updateInformation() {
        def user = User.get((springSecurityService.currentUser as User).id)
        PlayerProfile playerProfile = user.playerProfile ?: new PlayerProfile(user: user)
        playerProfile.properties = params
        // Prevent indiscriminate binding of fields ID and USER
        playerProfile.user = user
        playerProfile.id = user.playerProfile?.id
        if(request.isPost() && playerProfile.validate()) {
            playerProfile.save(flush: true)
            flash.message = "Informations mises a jour avec succes"
            redirect(mapping: 'my_account')
        }
        render(view: 'update_information', model: [playerProfile: playerProfile])
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

    @Secured('ROLE_ADMIN')
    def index() {
        def defaultParams = [sort: 'username', order: 'asc']
        [users: User.list(defaultParams << params)]
    }

    @Secured('ROLE_ADMIN')
    @Transactional
    def manageRoles() {
        def user = User.get(params.getLong('id'))
        if(request.isPost()) {
            List<String> roles = params.getList('roles[]')
            // Remove roles
            Set<String> rolesToAdd = roles
                    .findAll { !user.hasRole(it) }
            Set<String> rolesToRemove = user.roles
                    .findAll  { !roles.contains(it.authority) }
                    .collect { it.authority }
            log.info("Roles to add to ${user.username}: ${rolesToAdd.join(',')}")
            log.info("Roles to remove from ${user.username}: ${rolesToRemove.join(',')}")
            if(rolesToRemove.size() > 0) {
                UserRole.remove(user, rolesToRemove)
            }
            rolesToAdd.collect { Role.findByAuthority(it) }
                .each {role -> UserRole.create(user, role)}
            flash.message = "Roles mis à jour"
            user = User.get(user.id)
        }
        render(view: 'manageRoles', model: [user: user, roles: Role.findAll()])
    }

    @Secured('ROLE_ADMIN')
    @Transactional
    def delete(User user) {
        if(request.isPost()) {
            user.delete()
            flash.message = "Utilisateur ${user.username} supprime !"
        }
        redirect(action: 'index')
    }

    def show(User user) {
        if(params.slug != user.slug) {
            redirect(mapping: 'profile', id: user.id, params: [slug: user.slug])
        }
        render(view: 'show', model: [user: user])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def change_avatar() {
        def avatar = new Avatar()
        bindData(avatar, request)
        User me = springSecurityService.currentUser as User
        if(request.isPost() && avatar.validate()) {
            uploadAvatar(avatar, me)
            me.save()
            flash.message = "Avatar mis a jour !"
            redirect(mapping: 'my_account')
        }
        render(view: 'avatar', model: [
                me: me,
                avatar: avatar
        ])
    }

    private void uploadAvatar(Avatar avatar, User user) {
        if(avatar.file != null && !avatar.file.empty) {
            def fileName = "${user.avatarName}.${avatar.extension}"
            def resizedImage = Thumbnails.of(avatar.file.inputStream)
                    .size(200, 200)
                    .keepAspectRatio(false)
                    .asFiles([File.createTempFile('splatoon-', fileName)])[0]
            def url = uploadService.upload(fileName, resizedImage)
            user.avatar = url
        }
    }

}
