package splatoon

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'id')
class TournamentOrganizer {

    String name
    String website

    static hasMany = [tournaments: Tournament, members: User]

    static constraints = {
        name(blank: false)
        website(url: true)
    }

    @Override
    String toString() {
        return name
    }

    boolean canBeManagedBy(User user) {
        user.hasRole(Role.ROLE_ADMIN) || members.any { it.id == user.id }
    }
}
