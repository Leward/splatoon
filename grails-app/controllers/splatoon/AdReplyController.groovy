package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import  grails.gorm.transactions.Transactional
import org.springframework.security.access.AccessDeniedException

class AdReplyController {

    SpringSecurityService springSecurityService

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def create() {
        def recruitingAd = RecruitingAd.get(params.id)
        // Do not pass recruitingAd as parameter, we we to retrieve it from DB and not let grails do any data binding on it
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
    def edit() {
        def adReply = AdReply.get(params.id)
        if (!adReply.canEdit()) {
            throw new AccessDeniedException("Vous ne pouvez pas modifier cette réponse");
        }
        if (request.method == 'POST') {
            adReply.mergeProperties(new AdReply(params), springSecurityService.currentUser as User)
            if (adReply.validate()) {
                adReply.save(flush: true)
                redirect(mapping: 'recruitment_show_ad', id: adReply.ad.id)
            }
        } else {
            def viewModel = [
                    recruitingAd: adReply.ad,
                    adReply     : adReply
            ]
            render(view: 'edit', model: viewModel)
        }
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def delete(AdReply adReply) {
        if (!adReply.canDelete()) {
            throw new AccessDeniedException("Vous ne pouvez pas supprimer cette réponse");
        }
        if (request.method == 'POST') {
            def ad = adReply.ad
            adReply.delete()
            flash.message = "Commentaire supprimé"
            redirect(mapping: 'recruitment_show_ad', id: ad.id)
        }
    }

}
