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