package splatoon

import java.time.Instant

class Team {

    String name
    TeamType type = TeamType.TEAM
    Instant createdAt

    static belongsTo = [leader: User]

    static constraints = {
        name blank: false, unique: true
        createdAt nullable: true
        leader nullable: true // A can exist in the system but its leader is not registered in InkZone
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
}
