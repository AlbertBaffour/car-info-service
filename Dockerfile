FROM openjdk:17-jdk-alpine

EXPOSE 8051

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar

CMD ["java", "-jar", "/app.jar"]
