package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.access.annotation.Secured

class AdReplyController {

    SpringSecurityService springSecurityService

    @Secured('IS_AUTHENTICATED_FULLY')
    def create(RecruitingAd recruitingAd) {
        def adReply = new AdReply(params)
        adReply.id = null // params.id is not null we don't want it to interfere in our object
        adReply.author = springSecurityService.currentUser as User
        if(request.method == 'POST' && adReply.validate()) {
            adReply.save()
            redirect(mapping: 'recruitment_show_ad', id: recruitingAd.id)
        }
        def viewModel = [
                recruitingAd: recruitingAd,
                adReply: adReply
        ]
        render(view: 'create', model: viewModel)
    }
}
