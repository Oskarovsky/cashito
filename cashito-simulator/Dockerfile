FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /project
COPY . /project

RUN mvn clean package -DskipTests

# Use a lightweight JRE for running the app
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /project/target/cashito-simulator-0.0.1-SNAPSHOT.jar /app/app.jar

#run the spring boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
