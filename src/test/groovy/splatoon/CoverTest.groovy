package splatoon

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CoverTest extends Specification implements DomainUnitTest<Cover> {

    def "test validate with url with space)"() {
        setup:
        def cover = new Cover(name: 'Test', url: 'https://s3-eu-central-1.amazonaws.com/splatoon/7aca3a1f-e5b0-4d4f-b676-a633261c52bf-Inkzone cartoonized.png')
        expect:
        assert cover.validate()
    }

}
