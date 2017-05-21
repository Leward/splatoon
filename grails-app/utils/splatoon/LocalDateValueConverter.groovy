package splatoon

import grails.databinding.converters.ValueConverter
import groovy.transform.CompileStatic

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@CompileStatic
class LocalDateValueConverter implements ValueConverter {
    @Override
    boolean canConvert(Object value) {
        return value instanceof String && (value as String).matches('^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$')
    }

    @Override
    Object convert(Object value) {
        return LocalDate.parse(value as CharSequence, DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @Override
    Class<?> getTargetType() {
        return LocalDate
    }
}
