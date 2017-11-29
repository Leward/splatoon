package splatoon

import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class News {

    String title
    String content
    Instant date
    Cover cover

    def beforeInsert() {
        date = Instant.now()
    }

    Date getDateAsDateType() {
        return Date.from(date)
    }

    static constraints = {
        title blank: false
        content blank: false
        date nullable: true
        cover nullable: false
    }

    static mapping = {
        content type: 'text'
        cover fetch: 'join'
    }
}
