# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Create directory for the application
WORKDIR /app

# Copy the JAR file from the module's target directory
COPY target/cashito-infra-0.0.1-SNAPSHOT.jar app.jar

# Expose port (replace 8080 with your app's port)
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]