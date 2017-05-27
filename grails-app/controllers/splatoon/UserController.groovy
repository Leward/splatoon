package splatoon

import grails.transaction.Transactional

class UserController {

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



    def index() {
        [users: User.getAll()]
    }
}
