package splatoon

class TeamMember {

    String name
    String avatar
    PlayerType type

    static belongsTo = [team: Team]

    static hasMany = [roles: PlayerRole]

    static constraints = {
        team nullable: false
        avatar nullable: true
        type nullable: false
    }

    @Override
    String toString() {
        return name
    }
}
