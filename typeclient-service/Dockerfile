FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} typeclient-service.jar
ENTRYPOINT ["java","-jar","/typeclient-service.jar"]