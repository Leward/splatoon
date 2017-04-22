[![Build Status](https://travis-ci.org/Leward/splatoon.svg?branch=master)](https://travis-ci.org/Leward/splatoon)

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

* `SPLATOON_JDBC_URL` - JDBC Connection String, default to `mysql://localhost:3306/splatoon`
* `SPLATOON_DB_PASSWORD` - Database password for the `splatoon` user. Default to empty string (no password)

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