FROM maven:3.8-openjdk-11 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/target/country-management.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
