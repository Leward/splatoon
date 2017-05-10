package splatoon

import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class News {

    String title
    String content
    Instant date

    def beforeInsert() {
        date = Instant.now()
    }

    static constraints = {
        title blank: false
        content blank: false
        date nullable: true
    }

    static mapping = {
        content type: 'text'
    }
}
