package splatoon.website

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.context.annotation.Bean
import splatoon.LocalDateValueConverter
import splatoon.LocalTimeValueConverter

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    LocalTimeValueConverter localTimeConverter() {
        return new LocalTimeValueConverter()
    }

    @Bean
    LocalDateValueConverter localDateConverter() {
        return new LocalDateValueConverter()
    }
}