package splatoon

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class DateConversions {

    static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    static asDate(LocalDate date, LocalTime localTime) {
        LocalDateTime dateTime = LocalDateTime.of(date, localTime)
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())
    }

}
