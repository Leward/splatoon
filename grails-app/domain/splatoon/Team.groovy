package splatoon

import java.time.Instant

class Team {

    String name
    Instant createdAt

    static constraints = {
        name blank: false, unique: true
        createdAt nullable: true
    }

    static mapping = {
        sort 'name'
        createdAt updateable: false
    }

    def beforeInsert() {
        createdAt = Instant.now()
    }

    Date getCreatedAtAsDate() {
        return Date.from(createdAt)
    }

    @Override
    String toString() {
        return name
    }
}
