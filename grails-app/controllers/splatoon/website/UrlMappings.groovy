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
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
