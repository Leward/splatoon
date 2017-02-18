package splatoon

class UserController {

    def register() {
        if (request.method == "POST") {
            def user = new User(params)
            if(user.validate()) {
                user.save()
                // TODO: Redirect to a confirmation page
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
