### Stage 1: Build ###
FROM openjdk:23-jdk AS builder
WORKDIR /app

COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw clean package -DskipTests

### Stage 2: Run ###
FROM openjdk:23-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
