package splatoon

import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class Article {

    String title
    String content
    Instant date
    String cover = 'https://s3-eu-central-1.amazonaws.com/splatoon/0be69296-2b43-4c19-8d2e-b567df7f5fdb-image.png'

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
        cover url: true
    }

    static mapping = {
        content type: 'text'
    }
}
