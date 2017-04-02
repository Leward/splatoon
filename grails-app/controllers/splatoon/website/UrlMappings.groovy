package splatoon.website

import grails.plugin.springsecurity.LoginController
import splatoon.AdType

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
        name "recruitment": "/recrutement"(controller: "recruitment", action: "index")
        name "recruitment_new_teammate_search": "/recrutement/annonces/cherche-joueur/creer"(controller: "recruitment", action: "create_teammate_search_ad")
        name "recruitment_new_team_search": "/recrutement/annonces/cherche-team/creer"(controller: "recruitment", action: "create_team_search_ad")
        name "recruitment_show_ad": "/recrutement/annonces/$id"(controller: "recruitment", action: "show")
        name "recruitment_reply_ad": "/recrutement/annonces/$id/repondre"(controller: 'adReply', action: 'create')

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
