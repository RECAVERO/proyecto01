FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bankaccount-service.jar
ENTRYPOINT ["java","-jar","/bankaccount-service.jar"]