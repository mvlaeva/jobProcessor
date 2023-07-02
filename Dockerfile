# Use a base image with OpenJDK 17 pre-installed
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/jobProcessor-0.0.1-SNAPSHOT.jar /app/my-application.jar

# Run the Java application when the container starts
CMD ["java", "-jar", "my-application.jar"]
