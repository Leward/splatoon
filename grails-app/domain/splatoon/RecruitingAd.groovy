package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class RecruitingAd {

    transient SpringSecurityService springSecurityService

    AdType type
    String title
    String message
    String profileUrl
    Rank rank
    Instant createdAt

    static belongsTo = [author: User]

    def beforeInsert() {
        createdAt = Instant.now()
    }

    Date getCreatedAtAsDate() {
        return Date.from(createdAt)
    }

    String getShortMessage() {
        return HtmlStringUtils.truncateHtml(message, 50)
    }

    boolean canEdit(User user) {
        return user != null && (user.hasRole(Role.ROLE_ADMIN) || user.hasRole(Role.ROLE_MODERATOR) || user == author)
    }

    boolean canEdit() {
        canEdit(springSecurityService.currentUser as User)
    }

    boolean canDelete(User user) {
        return canEdit(user)
    }

    boolean canDelete() {
        canDelete(springSecurityService.currentUser as User)
    }

    static hasMany = [replies: AdReply]

    static mappedBy = [ad: AdReply]

    static mapping = {
        message(type: 'text')
        rank(column: '`rank`')
        sort(createdAt: "desc")
        author(updateable: false)
        createdAt(updateable: false)
    }

    static constraints = {
        type(nullable: false)
        title(blank: false)
        message(blank: false)
        profileUrl(nullable: true, url: true)
        rank(nullable: false)
        author(nullable: false)
        createdAt(nullable: true)
    }

}
