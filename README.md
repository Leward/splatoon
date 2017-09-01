[![Build Status](https://travis-ci.org/Leward/splatoon.svg?branch=master)](https://travis-ci.org/Leward/splatoon)
[![Heroku](https://heroku-badge.herokuapp.com/?style=flat&app=splatoon-portal)](https://splatoon-portal.herokuapp.com/)

## Frontend dependencies

Front-end dependencies are not managed by gradle itself. 
You need to have nodejs and npm installed.

When running `gradle assemble` or `gradle build` the `copyNpmFiles` 
will be automatically called. 

However running `grails run` won't do it. 

To install the frontend dependencies in the project run: `./gradlew copyNpmFiles`

## Docker

Build the image:
```bash
./gradlew assemble && docker image build -t leward/splatoon .
```

Run the app in a container in *dev* environment:
```bash
docker container run -p 8080:8080 -e JAVA_OPTS="-Dgrails.env=development" leward/splatoon
```

## Database 

In production environment the database can be configured using environment variables:

**[Option 1 - Legacy]**
* `SPLATOON_JDBC_URL` - JDBC Connection String, default to `mysql://localhost:3306/splatoon`
* `SPLATOON_DB_PASSWORD` - DB password for the `splatoon` user. Default to `splatoon` password

**[Option 2]**
* `JDBC_DATABASE_URL`  - JDBC Connection String, default to `mysql://localhost:3306/splatoon`
* `JDBC_DATABASE_USERNAME` DB username, defaults to `splatoon`
* `JDBC_DATABASE_PASSWORD` DB password, defaults to `splatoon`

Both MySQL and PostgreSQL are supported. Hibernate dialect and JDBC Driver will be picked based
on the JDBC URL.

### Start a MySQL Instance

To start a MySQL instance in a Docker container: 
```bash
docker container run -d \
  -p 3306:3306 \
  -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
  -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
  -e MYSQL_USER=splatoon \
  -e MYSQL_PASSWORD=splatoon \
  -e MYSQL_DATABASE=splatoon \
  --name splatoon-db \
  mysql:5.7;
```

### Start a PostgreSQL

```bash
docker container run -d \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=splatoon \
  -e POSTGRES_USER=splatoon \
  -e POSTGRES_DB=splatoon \
  --name splatoon-pg-db \
  postgres:9.6
```

## Uploaded files

Uploaded files are stored on AWS S3.
 
To have the upload feature to work, sepcify the following environment variables: 
* `AWS_ACCESS_KEY_ID`
* `AWS_SECRET_KEY`