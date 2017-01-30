package splatoon

import spock.lang.Specification

import java.time.LocalTime


class LocalTimeValueConverterSpec extends Specification {

    LocalTimeValueConverter localTimeConverter = new LocalTimeValueConverter()

    def "should only supports properly formatted strings"() {
        expect:
        localTimeConverter.canConvert(input) == canConvert
        where:
        input   | canConvert
        ""      | false
        "0"     | false
        "1245"  | false
        "0:0"   | true
        "14:59" | true
        ""      | false
    }

    def "should convert string to LocalTime object"() {
        expect:
        localTimeConverter.convert(input) == expectedValue
        where:
        input   | expectedValue
        "0:0"   | LocalTime.of(0, 0)
        "05:08" | LocalTime.of(5, 8)
        "15:14" | LocalTime.of(15, 14)
    }

}
