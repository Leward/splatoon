package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class AdReply {

    transient SpringSecurityService springSecurityService

    User author
    String message
    Instant createdAt

    static belongsTo = [ad: RecruitingAd]

    static constraints = {
        author(nullable: false)
        message(blank: false)
        createdAt(nullable: true)
    }

    static mappedBy = [ad: "replies"]

    def beforeInsert() {
        createdAt = Instant.now()
    }

    def mergeProperties(AdReply modifiedAdReply, User updatedBy) {
        if (hasFullControl(updatedBy)) {
            author = modifiedAdReply.author
            ad = modifiedAdReply.ad
            createdAt = modifiedAdReply.createdAt
        }
        message = modifiedAdReply.message
    }

    Date getCreatedAtAsDate() {
        return Date.from(createdAt)
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

    boolean hasFullControl(User user) {
        return user.hasRole('ROLE_ADMIN')
    }
}
