package splatoon.website

import grails.plugin.springsecurity.LoginController

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // Regular routes
        name "index": "/"(view:"/index")
//        name "registration": "/inscription"(controller: "user", action: "register")
        name "registration_success": "/inscription/confirmation"(view: "/user/register_success")

        // Spring Security routes
        name "login": "/login/auth"(controller: "login", action: "auth")

        // For dev
        name "dev": "/dev"(view:"/dev")
        name "styleguide": "/styleguide"(view:"/styleguide")

        // Admin routes
        name "admin": "/admin"(view: "/admin")
        name "admin_tournamentOrganizer_list": "/admin/organisateurs" (controller: "tournamentOrganizer", action: "index")
        name "admin_tournamentOrganizer_add": "/admin/organisateurs/ajouter" (controller: "tournamentOrganizer", action: "create")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
