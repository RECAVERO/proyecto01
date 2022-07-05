FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} credit-service.jar
ENTRYPOINT ["java","-jar","/credit-service.jar"]