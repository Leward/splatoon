package splatoon

class ArticleCategory {

    String name

    static constraints = {
        name nullable: false, blank: false, unique: true
    }

    @Override
    String toString() {
        return name
    }
}
