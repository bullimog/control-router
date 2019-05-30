# Control Router
Spring Boot application to control a remove web form. Accessible on port 8080, by default

Build and package:
> mvn clean package

Execute:
> java -jar ./target/control-router-0.0.1-SNAPSHOT.jar


For http://localhost:8081/myservice/family-back/{familyId}
Can also start another instance, to demonstrate service-to-service communication:

> java -jar -Dserver.port=8081 ./target/control-router-0.0.1-SNAPSHOT.jar



To Build a Docker image:
> docker build -t control-router .

To run the image within a Docker container:
> docker run --rm -p 8080:8080 -it control-router:latest

