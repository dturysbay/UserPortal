FROM ubuntu:latest AS build
FROM openjdk:17-jdk-slim
FROM dturysbay/user-portal-iso

ENTRYPOINT ["java", "-jar", "user-portal.jar"]
