package splatoon.website

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        name "index": "/"(view:"/index")
        name "dev": "/dev"(view:"/dev")
        name "styleguide": "/styleguide"(view:"/styleguide")

        name "admin": "/admin"(view: "/admin")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
