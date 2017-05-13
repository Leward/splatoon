package splatoon

import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class RecruitingAd {

    AdType type
    String title
    String message
    String profileUrl
    User author
    Instant createdAt

    def beforeInsert() {
        createdAt = Instant.now()
    }

    Date getCreatedAtAsDate() {
        return Date.from(createdAt)
    }

    String getShortMessage() {
        return HtmlStringUtils.truncateHtml(message, 50)
    }

    static hasMany = [replies: AdReply]

    static mappedBy = [ad: AdReply]

    static mapping = {
        message(type: 'text')
        sort(createdAt: "desc")
    }

    static constraints = {
        type(nullable: false)
        title(blank: false)
        message(blank: false)
        profileUrl(nullable: true, url: true)
        author(nullable: false)
        createdAt(nullable: true)
    }

}
