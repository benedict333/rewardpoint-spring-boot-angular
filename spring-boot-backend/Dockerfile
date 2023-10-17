# Use the official AdoptOpenJDK base image for Java 17 (HotSpot)
FROM openjdk:17-jdk-slim-buster

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/rewardpoints-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application's port (change it as needed)
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
