package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import grails.gorm.transactions.Transactional
import org.springframework.security.access.annotation.Secured

import java.nio.file.AccessDeniedException
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalUnit

class RecruitmentController {

    SpringSecurityService springSecurityService

    def index() {
        def pageSize = 10
        def pages = [
                playerLookingForTeam: params.searchTeamPage ?: 1,
                teamLookingForPlayer: params.searchTeammatePage ?: 1
        ]
        def queryConfig = [
                playerLookingForTeam: [offset: (pages.playerLookingForTeam - 1) * pageSize, max: pageSize],
                teamLookingForPlayer: [offset: (pages.teamLookingForPlayer - 1) * pageSize, max: pageSize]
        ]

        def searchTeammateAds = RecruitingAd.findAllByType(AdType.LOOKING_FOR_TEAMMATE_AD, queryConfig.teamLookingForPlayer)

        def usersLookingForATeam = PlayerProfile.findAllByLookingForATeam(true, queryConfig.teamLookingForPlayer)
                .collect { it.user }

        def viewModel = [searchTeammateAds   : searchTeammateAds,
                         usersLookingForATeam: usersLookingForATeam]
        render(view: "index", model: viewModel)
    }

    def show(RecruitingAd recruitingAd) {
        def viewModel = [
                recruitingAd: recruitingAd,
                adReply     : new AdReply(),
                replies     : AdReply.findAllByAd(recruitingAd)
        ]
        render(view: "show", model: viewModel)
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def edit(RecruitingAd ad) {
        if (!ad.canEdit()) {
            throw new AccessDeniedException("Vous ne pouvez pas modifier cette annonce");
        }
        if (request.isPost()) {
            ad.save()
            redirect(mapping: 'recruitment_show_ad', id: ad.id)
        }
        render(view: 'edit', model: [recruitingAd: ad])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    @Transactional
    def delete(RecruitingAd ad) {
        if (!ad.canDelete()) {
            throw new AccessDeniedException("Vous ne pouvez pas modifier cette annonce");
        }
        if (request.isPost()) {
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
        render(view: "create_team_search_ad", model: [recruitingAd: ad])
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
        render(view: "create_teammate_search_ad", model: [recruitingAd: ad])
    }

    @Secured('IS_AUTHENTICATED_FULLY')
    def replyToAd(AdReply adReply) {
        adReply.author = springSecurityService.currentUser as User
        if (adReply.validate()) {
            adReply.save()
        }
    }

    def searchPlayers(PlayerSearch playerSearch) {
        def past = LocalDate.now(ZoneId.of("Europe/Paris")).minusYears(7)
        def results = playerSearch.generateCriteria().list()
        def users = results.collect { it.user }
        render(view: "search", model: [search: playerSearch, users: users])
    }
}
