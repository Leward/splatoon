package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import java.nio.file.AccessDeniedException

class RecruitmentController {

    SpringSecurityService springSecurityService

    def index() {
        def pageSize = 10
        def searchTeamPage = params.searchTeamsPage ?: 1
        def searchTeammatePagination = params.searchTeammatePage ?: 1
        def searchTeamAds = RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAM_AD,
                [offset: (searchTeamPage - 1) * pageSize, max: pageSize])
        def searchTeammateAds = RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAMMATE_AD,
                [offset: (searchTeammatePagination - 1) * pageSize, max: pageSize])
        def viewModel = [searchTeamAds: searchTeamAds, searchTeammateAds: searchTeammateAds]
        render(view: "index", model: viewModel)
    }

    def show(RecruitingAd recruitingAd) {
        def viewModel = [
                recruitingAd: recruitingAd,
                adReply: new AdReply(),
                replies: AdReply.findAllByAd(recruitingAd)
        ]
        render(view: "show", model: viewModel)
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def edit(RecruitingAd ad) {
        if(!ad.canEdit()) {
            throw new AccessDeniedException("Vous ne pouvez pas modifier cette annonce");
        }
        if(request.isPost()) {
            ad.save()
            redirect(mapping: 'recruitment_show_ad', id: ad.id)
        }
        render(view: 'edit', model: [recruitingAd: ad])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def delete(RecruitingAd ad) {
        if(!ad.canDelete()) {
            throw new AccessDeniedException("Vous ne pouvez pas modifier cette annonce");
        }
        if(request.isPost()) {
            ad.delete()
            flash.message = "Annonce supprimée"
            redirect(mapping: 'recruitment')
        }
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def create_team_search_ad() {
        def ad = new RecruitingAd(params)
        ad.author = springSecurityService.currentUser as User
        ad.type = AdType.LOOKING_FOR_TEAM_AD
        if (request.method == 'POST') {
            if (ad.validate()) {
                ad.save()
                redirect(mapping: 'recruitment_show_ad', id: ad.id)
            }
        }
        render(view: "create_team_search_ad", model: [recruitingAd:ad])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def create_teammate_search_ad() {
        def ad = new RecruitingAd(params)
        ad.author = springSecurityService.currentUser as User
        ad.type = AdType.LOOKING_FOR_TEAMMATE_AD
        if (request.method == 'POST') {
            if (ad.validate()) {
                ad.save()
                redirect(mapping: 'recruitment_show_ad', id: ad.id)
            }
        }
        render(view: "create_teammate_search_ad", model: [recruitingAd:ad])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    def replyToAd(AdReply adReply) {
        adReply.author = springSecurityService.currentUser as User
        if(adReply.validate()) {
            adReply.save()
        }
    }
}
