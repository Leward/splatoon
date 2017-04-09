package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.access.annotation.Secured

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
        respond ad
    }

    @Secured('IS_AUTHENTICATED_FULLY')
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
        respond ad
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    def replyToAd(AdReply adReply) {
        adReply.author = springSecurityService.currentUser as User
        if(adReply.validate()) {
            adReply.save()
        }
    }
}