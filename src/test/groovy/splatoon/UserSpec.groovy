package splatoon

import spock.lang.Specification;

class UserSpec extends Specification {

    void "test equals"() {
        expect:
        (a == b) == shouldBeEqual
        where:
        a                                   | b                                 | shouldBeEqual
        new User()                          | null                              | false
        new User()                          | new User()                        | false
        new User(id: 1)                     | new User(id: 2)                   | false
        new User(id: 1, username: "Albert") | new User(id: 1, username: "Toto") | false
    }

}