package splatoon

import groovy.transform.EqualsAndHashCode
import org.apache.commons.validator.routines.UrlValidator

@EqualsAndHashCode(includes = 'id')
class TournamentOrganizer {

    String name
    String website

    static hasMany = [tournaments: Tournament, members: User]

    static constraints = {
        name(blank: false)
        website(validator: { new UrlValidator().isValid(it) })
    }

    @Override
    String toString() {
        return name
    }

    boolean canBeManagedBy(User user) {
        user.hasRole(Role.ROLE_ADMIN) || members.any { it.id == user.id }
    }

    TournamentOrganizer withId(Long id) {
        this.id = id
        return this
    }
}
