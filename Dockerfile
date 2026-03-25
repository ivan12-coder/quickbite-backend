# Use Java 25 as the base image
FROM openjdk:25-jdk-slim

# Install maven (since mvnw might not work)
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

# Copy pom.xml first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/*.jar"]