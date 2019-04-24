# Control Router
Spring Boot application to control a remove web form.

Build and package:
> mvn clean package

Execute:
> java -jar ./target/control-router-0.0.1-SNAPSHOT.jar


For http://localhost:8081/myservice/family-back/{familyId}
Can also start another instance, to demonstrate service-to-service communication:

> java -jar -Dserver.port=8081 ./target/control-router-0.0.1-SNAPSHOT.jar

