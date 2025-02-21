
FROM openjdk:21-jdk
LABEL maintainer="stella6767"
ARG JAR_FILE=build/libs/free-ai-web-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /free-webui.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker","/free-webui.jar"]
