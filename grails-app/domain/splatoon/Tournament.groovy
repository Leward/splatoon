package splatoon

class Tournament {

    String name
    TournamentOrganizer organizer
    List<TournamentEvent> events // Enforce a List instead of default Set to keep ordering

    static hasMany = [events: TournamentEvent]

    static constraints = {
        organizer(nullable: false)
    }

    static mapping = {
        events sort: 'date', order: 'asc'
    }

    @Override
    String toString() {
        return name
    }
}
