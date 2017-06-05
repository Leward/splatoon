package splatoon

class Team {

    String name

    static constraints = {
        name blank: false, unique: true
    }

    @Override
    String toString() {
        return name
    }
}
