package splatoon

import grails.databinding.converters.ValueConverter
import groovy.transform.CompileStatic

import java.time.LocalTime

@CompileStatic
class LocalTimeValueConverter implements ValueConverter {
    @Override
    boolean canConvert(Object value) {
        return value instanceof String && value.matches('^[0-9]{1,2}:[0-9]{1,2}$')
    }

    @Override
    Object convert(Object value) {
        def exploded = ((String)value).split(":")
        def h = Integer.parseInt(exploded[0])
        def min = Integer.parseInt(exploded[1])
        return LocalTime.of(h, min)
    }

    @Override
    Class<?> getTargetType() {
        return LocalTime
    }
}
