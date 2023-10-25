# Use a base image with Java 8 and Maven
FROM maven:3.6.3-jdk-8-slim

# Set the working directory in the container
WORKDIR /app

# Copy the entire project (including the pom.xml) into the container
COPY . /app

# Build the Java application using Maven
RUN mvn clean package

# Expose the port that your Java application is running on (if applicable)
EXPOSE 8080

# Command to run the Java application
CMD ["java", "-jar", "target/legacy-java-application-0.0.1-SNAPSHOT.jar"]
