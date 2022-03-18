FROM openjdk:17-jdk-alpine

# Set image information, but could be set to different location from command line
ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="Posts API"
ARG MAINTAINER="JaeWang Lee <jnso5072@outlook.kr>"

LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./app.jar app.jar

# Expose the service to port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]
