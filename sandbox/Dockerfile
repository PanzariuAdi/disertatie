# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:resolve dependency:resolve-plugins

# Copy the source code to the container
COPY src ./src

# Package the application
RUN mvn -B clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/sandbox-0.0.1-SNAPSHOT.jar /app/sandbox-0.0.1-SNAPSHOT.jar

# Copy the graph4j-1.0.7.jar library to the container
COPY graph4j-1.0.7.jar /app/graph4j-1.0.7.jar

# Copy additional files
COPY src/main/resources/datasets /app/datasets

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/sandbox-0.0.1-SNAPSHOT.jar"]


