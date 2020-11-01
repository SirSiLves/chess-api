
FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# PRODUCTION DEPLOYMENT
# maven -> clean -> install
# docker build -t chess-api-docker .
# docker save chess-api-docker:latest -o "V:\RSLS\Docker\export\chess-api-docker.tar" chess-api-docker

