# Control Router
Spring Boot application to control a remote web form. Accessible on port 8080, by default

Build and package:
> mvn clean package

Execute:
> java -jar ./target/control-router-0.0.1-SNAPSHOT.jar


For http://localhost:8081/myservice/family-back/{familyId}
Can also start another instance, to demonstrate service-to-service communication:

> java -jar -Dserver.port=8081 ./target/control-router-0.0.1-SNAPSHOT.jar



To Build a Docker image:
> docker build -t control-router .

To run the image within a Docker container: (auto remove container on exit; map port 8080 to the outside; create a pseudo-TTY for container stdin)
> docker run -p 80:8080 --rm -it control-router:latest


# HomeController Endpoints
* GET /
* GET /family
* GET /forward
* GET /getDataAndTime
* GET /greeting
* GET /page
* GET /international

# JsonController Endpoints
* GET  /myservice/family-back/{familyId}
* GET  /myservice/family/{familyId}
* POST /myservice/family

