FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY ./build/libs/*.jar app.jar
#ARG PROFILE=prod
#ARG PROFILE=local
ENV PROFILE=${PROFILE}

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:./pinpoint/pinpoint-bootstrap-2.2.2.jar", "-jar", "/app.jar", "-Dpinpoint.agentId=gjgs01", "-Dpinpoint.applicationName=gjgs", "-Dpinpoint.config=./pinpoint/pinpoint-root.config"]
