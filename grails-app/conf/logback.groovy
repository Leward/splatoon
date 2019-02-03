import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter
import java.nio.charset.Charset
import de.siegmar.logbackgelf.GelfUdpAppender
import de.siegmar.logbackgelf.GelfLayout

// See http://logback.qos.ch/manual/groovy.html for details on configuration
def env = System.getenv()
def configuredAppenders = ['STDOUT']

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')
        if (Environment.isDevelopmentMode()) {
            pattern = "%level %logger - %msg%n"
        } else {
            pattern = '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
        }
    }
}


if(env['GELF_OVH_TOKEN']) {
    println "\$GELF_OVH_TOKEN = ${env['GELF_OVH_TOKEN']}"
    appender('GELF', GelfUdpAppender) {
        graylogHost = "gra2.logs.ovh.com"
        graylogPort = 2202
        layout(GelfLayout) {
            includeLevelName = true
            staticFields = ["X-OVH-TOKEN" : env['GELF_OVH_TOKEN']]
        }
    }
    configuredAppenders.add('GELF');
}

if(env['SPLATOON_LOG_FOLDER']) {
    println "Log folder -> ${env['SPLATOON_LOG_FOLDER']}/inkzone.log"
     appender('FILE', FileAppender) {
         file = "${env['SPLATOON_LOG_FOLDER']}/inkzone.log"
         append = true
         encoder(PatternLayoutEncoder) {
             charset = Charset.forName('UTF-8')
             pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %level %logger - %msg%n"
         }
     }
    configuredAppenders.add('FILE')
}

println "Appenders: ${configuredAppenders}"
root(INFO, configuredAppenders)
