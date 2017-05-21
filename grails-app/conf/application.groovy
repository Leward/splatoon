grails {
    profile = 'web'
    codegen {
        defaultPackage = splatoon.website
    }
    plugin {
        awssdk {
            region = 'eu-central-1'
        }
        springsecurity {
            userDomainClassName = 'splatoon.User'
            authorityJoinClassName = 'splatoon.UserRole'
            authority {
                className = 'splatoon.Role'
            }
            securityConfigType = 'Annotation'
            logout {
                postOnly = false
            }
            rejectIfNoRule = false
            fii {
                rejectPublicInvocations = false
            }
        }
    }
    controllers {
        upload {
            maxFileSize = 10000000
            maxRequestSize = 10000000
        }
    }
    mime {
        types {
            html = ['text/html', 'application/xhtml+xml']
        }
    }
}

hibernate {
    cache {
        queries = false
        use_second_level_cache = true
        use_query_cache = false
        region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'
    }
}

dataSource {
    pooled = true
}

environments {
    production {
        // Environment variables injected by Heroku:
        // - JDBC_DATABASE_URL
        // - JDBC_DATABASE_USERNAME
        // - JDBC_DATABASE_PASSWORD
        def jdbcUrl = System.env.JDBC_DATABASE_URL ?: System.env.SPLATOON_JDBC_URL ?: 'jdbc:mysql://localhost:3306/splatoon'
        dataSource {
            dbCreate = 'none'
            url = jdbcUrl
            if (jdbcUrl.contains('mysql')) {
                driverClassName = 'com.mysql.jdbc.Driver'
                dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'
            } else {
                driverClassName = 'org.postgresql.Driver'
                dialect = 'org.hibernate.dialect.PostgreSQLDialect'
            }
            username = System.env.JDBC_DATABASE_USERNAME ?: 'splatoon'
            password = System.env.JDBC_DATABASE_PASSWORD ?: System.env.SPLATOON_DB_PASSWORD ?: 'splatoon'
        }
        flyway {
            enabled = true
            locations = jdbcUrl.contains('mysql') ? 'db/migration/mysql' : 'db/migration/postgresql'
            baselineOnMigrate = false
        }
    }
}
development {
    dataSource {
        dbCreate = 'update'
        driverClassName = 'org.h2.Driver'
        username = 'sa'
        password = ''
    }
}