FROM openjdk:17-jdk-alpine

ARG JAR_FILE=build/libs/bookstore-0.0.1-SNAPSHOT.jar

ARG PROFILES

ARG ENV

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-Dserver.env=${ENV}", "-jar", "app.jar"]