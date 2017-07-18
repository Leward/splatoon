package splatoon

class Tournament {

    String name
    TournamentOrganizer organizer
    Game game = Game.SPLATOON_1
    String rules
    String registrationDetails
    List<TournamentEvent> events // Enforce a List instead of default Set to keep ordering

    static hasMany = [events: TournamentEvent]

    static constraints = {
        organizer(nullable: false)
    }

    static mapping = {
        events sort: 'date', order: 'asc'
        rules type: 'text'
        registrationDetails type: 'text'
    }

    @Override
    String toString() {
        return name
    }
}
