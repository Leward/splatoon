package splatoon

import groovy.transform.builder.Builder

import java.time.Instant

@Builder
class Article {

    String title
    String content
    Instant date
    Cover cover
    ArticleCategory category

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
        category nullable: false
    }

    static mapping = {
        content type: 'text'
    }
}
