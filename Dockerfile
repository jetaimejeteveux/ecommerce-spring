FROM openjdk:21-jdk-slim

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src src/

RUN chmod +x mvnw
RUN ./mvnw package -Dskiptests

RUN cp target/*.jar app.jar
EXPOSE 8080