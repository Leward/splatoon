package splatoon

import grails.validation.Validateable
import groovy.transform.InheritConstructors
import groovy.transform.ToString

@InheritConstructors
@ToString
class ProfileInformation implements Validateable {

    String nintendoId

    ProfileInformation(User user) {
        nintendoId = user.nintendoId
    }

    static constraints = {
        nintendoId nullable: true, size: 0..100
    }

}
