package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

class AdReplyController {

    SpringSecurityService springSecurityService

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def create() {
        def recruitingAd = RecruitingAd.get(params.id) // Do not pass recruitingAd as parameter, we we to retrieve it from DB and not let grails do any data binding on it
        def adReply = new AdReply(params)
        adReply.id = null // params.id is not null we don't want it to interfere in our object
        adReply.author = springSecurityService.currentUser as User
        if (request.method == 'POST' && adReply.validate()) {
            adReply.save()
            redirect(mapping: 'recruitment_show_ad', id: recruitingAd.id)
        }
        def viewModel = [
                recruitingAd: recruitingAd,
                adReply     : adReply
        ]
        render(view: 'create', model: viewModel)
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def edit(AdReply adReply) {
        if (!adReply.canEdit(springSecurityService.currentUser as User)) {
            render(status: 403)
        }
        if (request.method == 'POST') {
            adReply.mergeProperties(new AdReply(params), springSecurityService.currentUser as User)
            if (adReply.validate()) {
                adReply.save(flush: true)
                redirect(mapping: 'recruitment_show_ad', id: adReply.ad.id)
            }
        }
        def viewModel = [
                recruitingAd: adReply.ad,
                adReply     : adReply
        ]
        render(view: 'edit', model: viewModel)
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def delete(AdReply adReply) {
        if (!adReply.canDelete(springSecurityService.currentUser as User)) {
            render(status: 403)
        }
        if(request.method == 'POST') {
            def ad = adReply.ad
            adReply.delete()
            redirect(mapping: 'recruitment_show_ad', id: ad.id)
        }
    }

}
