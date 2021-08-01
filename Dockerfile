FROM openjdk:11-jdk-slim
MAINTAINER Elvis Fernandes
RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
COPY ./target/app.jar $PROJECT_HOME
EXPOSE 8080
WORKDIR  $PROJECT_HOME
ENTRYPOINT ["java", "-jar", "app.jar"]