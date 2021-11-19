FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/demo.jar /usr/local/lib/demo.jar
COPY docker_entrypoint.sh /usr/local/lib/docker_entrypoint.sh
CMD chmod +x /usr/local/lib/docker_entrypoint.sh
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["/bin/bash", "/usr/local/lib/docker_entrypoint.sh"]