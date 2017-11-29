package splatoon

class Tournament {

    String name
    TournamentOrganizer organizer
    Game game = Game.SPLATOON_1
    String rules
    String registrationDetails
    Set<TournamentEvent> events // Enforce a List instead of default Set to keep ordering

    static hasMany = [events: TournamentEvent]

    static constraints = {
        organizer(nullable: false)
    }

    static mapping = {
        events sort: 'date', order: 'asc'
        rules type: 'text'
        registrationDetails type: 'text'
        organizer fetch: 'join'
    }

    Tournament withId(Long id) {
        this.id = id
        return this
    }

    @Override
    String toString() {
        return name
    }
}
