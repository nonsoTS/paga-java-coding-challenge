# Use a JDK 21 base image
FROM eclipse-temurin:21-jdk

# Copy the built jar file into the container
COPY target/farm-collector-1.0.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "/app.jar"]