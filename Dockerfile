# Use Java 25 as the base image
FROM openjdk:25-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file from target folder
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]