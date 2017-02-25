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
        name "tournament_event": "/evenement/$id"(controller: "tournamentEvent", action: "details")

        // Spring Security routes
        name "login": "/login/auth"(controller: "login", action: "auth")

        // For dev
        name "dev": "/dev"(view:"/dev")
        name "styleguide": "/styleguide"(view:"/styleguide")

        // Admin routes
        name "admin": "/admin"(view: "/admin")
        name "admin_tournamentOrganizer_list": "/admin/organisateurs" (controller: "tournamentOrganizer", action: "index")
        name "admin_tournamentOrganizer_add": "/admin/organisateurs/ajouter" (controller: "tournamentOrganizer", action: "create")
        name "admin_tournament_list": "/admin/tournois" (controller: "tournament", action: "index")
        name "admin_tournament_add": "/admin/tournois/ajouter" (controller: "tournament", action: "create")
        name "admin_tournament_show": "/admin/tournois/$id" (controller: "tournament", action: "show")
        name "admin_tournament_show": "/admin/tournois/$id/modifier" (controller: "tournament", action: "edit")
        name "admin_tournamentEvent_show": "/admin/tournois/evenements/$id" (controller: "tournamentEvent", action: "show")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
