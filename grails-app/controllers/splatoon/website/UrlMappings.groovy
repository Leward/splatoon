package splatoon.website

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // Regular routes
        name "index": "/"(controller: 'home', action: 'index')
        name "registration": "/inscription"(controller: "user", action: "register")
        name "registration_success": "/inscription/confirmation"(view: "/user/register_success")
        name "my_account": "/mon-espace"(controller: 'user', action: 'myAccount')
        name "change_password": "/modifier-mot-de-passe"(controller: 'user', action: 'changePassword')
        name "tournament_event": "/evenement/$id"(controller: "tournamentEvent", action: "details")
        name "recruitment": "/recrutement"(controller: "recruitment", action: "index")
        name "recruitment_new_teammate_search": "/recrutement/annonces/cherche-joueur/creer"(controller: "recruitment", action: "create_teammate_search_ad")
        name "recruitment_new_team_search": "/recrutement/annonces/cherche-team/creer"(controller: "recruitment", action: "create_team_search_ad")
        name "recruitment_show_ad": "/recrutement/annonces/$id"(controller: "recruitment", action: "show")
        name "recruitment_reply_ad": "/recrutement/annonces/$id/repondre"(controller: 'adReply', action: 'create')
        name "recruitment_reply_ad_edit": "/recrutement/annonces/responses/$id/modifier"(controller: 'adReply', action: 'edit')
        name "news_show": "/news/$id"(controller: 'news', action: 'show')
        name "ladder": "/ladder"(controller: 'ladder', action: 'index')

        // Spring Security routes
        name "login": "/login/auth"(controller: "login", action: "auth")
        name "forgotten_password": "/mot-de-passe-oublie"(controller: 'user', action: 'forgottenPassword')

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
        name "admin_news": "/admin/news" (controller: 'news', action: 'admin_index')
        name "admin_news_create": "/admin/news/creer" (controller: 'news', action: 'admin_create')
        name "admin_news_show": "/admin/news/$id" (controller: 'news', action: 'admin_show')
        name "admin_news_edit": "/admin/news/$id/modifier" (controller: 'news', action: 'admin_update')
        name "admin_news_delete": "/admin/news/$id/supprimer" (controller: 'news', action: 'admin_delete')
        name "admin_ladder": "/admin/ladder"(view: '/ladder/admin')
        name "admin_ladder_event_details": "/admin/ladder/$id"(controller: 'ladder', action: 'adminEventDetails')
        name "admin_ladder_add_result": "/admin/ladder/$eventId/ajouter-resultat"(controller: 'ladder', action: 'create')
        name "admin_ladder_update_result": "/admin/ladder/$id/modifier-resultat"(controller: 'ladder', action: 'update')
        name "admin_team": "/admin/equipes"(controller: 'team', action: 'index')

        // Others
        name 'upload': '/upload' (controller: 'upload', action: 'index')
        name 'twitch_placeholder': '/twitch_placeholder' (view: '/twitch_placeholder')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
