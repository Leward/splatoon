package splatoon

import java.time.Instant

class AdReply {

    User author
    String message
    Instant createdAt

    static belongsTo = [ad: RecruitingAd]

    static constraints = {
        author(nullable: false)
        message(blank: false)
        createdAt(nullable: true)
    }

    def beforeInsert() {
        createdAt = Instant.now()
    }

    Date getCreatedAtAsDate() {
        return Date.from(createdAt)
    }
}
