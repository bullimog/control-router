#
# Build stage
# Build on local machine, instead of in Docker...
# FROM maven:3.6.1-jdk-8 AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# COPY application.properties /home/app
# RUN mvn -e -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
#COPY --from=build /home/app/target/control-router-0.0.1-SNAPSHOT.jar /usr/local/lib/control-router.jar
COPY target/control-router-0.0.1-SNAPSHOT.jar /usr/local/lib/control-router.jar
COPY application.properties application.properties
EXPOSE 8080
ENTRYPOINT ["java","--add-opens=java.base/java.lang=ALL-UNNAMED", "--add-opens=java.base/java.io=ALL-UNNAMED", "--add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED", "-jar","/usr/local/lib/control-router.jar"]

# --add-opens were added to avoid Illegal reflective access Warnings

