package splatoon

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime
import java.time.Month


class LocalDateValueConverterSpec extends Specification {

    LocalDateValueConverter localDateConverter = new LocalDateValueConverter()

    def "should only supports properly formatted strings"() {
        expect:
        localDateConverter.canConvert(input) == canConvert
        where:
        input        | canConvert
        ""           | false
        "0"          | false
        "2015"       | false
        "01-01-1990" | false
        "2015-05-01" | true
    }

    def "should convert string to LocalTime object"() {
        expect:
        localDateConverter.convert(input) == expectedValue
        where:
        input   | expectedValue
        "1990-01-01" | LocalDate.of(1990, Month.JANUARY, 1)
        "2017-06-15" | LocalDate.of(2017, Month.JUNE, 15)
    }

}
