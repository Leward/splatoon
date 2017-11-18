package splatoon

import java.time.Instant

class Team {

    String name
    TeamType type = TeamType.TEAM
    Instant createdAt

    /**
     * The country code of the team.
     * This is the  <a href="https://www.iso.org/obp/ui/#search/code/">ISO 3166-1-alpha-2 code</a>
     * of a country
     */
    String countryCode

    static belongsTo = [leader: User]

    static constraints = {
        name blank: false, unique: true
        createdAt nullable: true
        leader nullable: true // A can exist in the system but its leader is not registered in InkZone
        countryCode inList: ['fr', 'eu', 'be']
    }

    static mapping = {
        sort 'name'
        type nullable: false
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

    boolean canBeManagedBy(User user) {
        return user.hasRole('ROLE_ADMIN') || user.hasRole('ROLE_TO') || user == leader
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Team team = (Team) o

        if (id == null || id != team.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : super.hashCode())
    }
}
