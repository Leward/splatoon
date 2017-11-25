package splatoon

import spock.lang.Specification

import java.time.Month

class MonthYearSpec extends Specification {

    static int IS_SAME = 0
    static int IS_BIGGER = 1
    static int IS_SMALLER = -1

    def "test equality"() {
        expect:
        new MonthYear(Month.JULY, 2015) == new MonthYear(Month.JULY, 2015)
    }

    def "go to next month"() {
        expect:
        new MonthYear(month, year).next() == new MonthYear(newMonth, newYear)
        where:
        month          | year | newMonth       | newYear
        Month.JANUARY  | 2000 | Month.FEBRUARY | 2000
        Month.MARCH    | 2015 | Month.APRIL    | 2015
        Month.DECEMBER | 2004 | Month.JANUARY  | 2005
    }

    def "go to previous month"() {
        expect:
        new MonthYear(month, year).previous() == new MonthYear(newMonth, newYear)
        where:
        month          | year | newMonth       | newYear
        Month.JANUARY  | 2000 | Month.DECEMBER | 1999
        Month.MARCH    | 2015 | Month.FEBRUARY | 2015
        Month.DECEMBER | 2004 | Month.NOVEMBER | 2004
    }

    def "is comparable"() {
        expect:
        new MonthYear(month1, year1).compareTo(new MonthYear(month2, year2)) == result
        where:
        month1         | year1 | month2         | year2 | result
        Month.JANUARY  | 2000  | Month.FEBRUARY | 2000  | IS_SMALLER
        Month.MARCH    | 2000  | Month.FEBRUARY | 2000  | IS_BIGGER
        Month.FEBRUARY | 2000  | Month.FEBRUARY | 2000  | IS_SAME
        Month.JANUARY  | 2001  | Month.FEBRUARY | 2000  | IS_BIGGER
        Month.MARCH    | 1999  | Month.FEBRUARY | 2000  | IS_SMALLER
    }

    def "to string"() {
        expect:
        new MonthYear(Month.APRIL, 2003).toString() == "4/2003"
    }

    def "conversion to date"() {
        expect:
        new MonthYear(Month.APRIL, 2003).toDate().month == 3
        new MonthYear(Month.APRIL, 2003).toDate().year == 103
    }

}
