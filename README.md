# simple-gis

### Introduction
Simple urban gis web-service with persistence data and client library.

#### Building and Running Application

To build a web service use provided maven wrapper:
```$./mvnw clean install```

It will generate runnable {project.root}/service/target/simplegis-service.jar
and non runnable {project.root}/library/target/library-0.0.1-SNAPSHOT.jar to ship as a library.


Also it could be run as:
```$./mvnw -pl service -P local spring-boot:run```