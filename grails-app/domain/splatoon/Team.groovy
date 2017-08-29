package splatoon

class Team {

    String name

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        sort 'name'
    }

    @Override
    String toString() {
        return name
    }
}
