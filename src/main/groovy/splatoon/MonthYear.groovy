package splatoon

import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable

import java.time.LocalDate
import java.time.Month

@Sortable(includes = ['year', 'month'])
@EqualsAndHashCode
class MonthYear {

    final Month month
    final int year

    MonthYear(Month month, int year) {
        this.month = month
        this.year = year
    }

    static now() {
        def date = LocalDate.now(TournamentEvent.PARIS_ZONE_ID)
        return new MonthYear(date.month, date.year)
    }

    MonthYear next() {
        Month newMonth = month.plus(1)
        int newYear = year
        if(newMonth == Month.JANUARY) {
            newYear = year + 1
        }
        return new MonthYear(newMonth, newYear)
    }

    MonthYear previous() {
        Month newMonth = month.minus(1)
        int newYear = year
        if(newMonth == Month.DECEMBER) {
            newYear = year - 1
        }
        return new MonthYear(newMonth, newYear)
    }

    @Override
    String toString() {
        return "${month.value}/${year}"
    }
}
