FROM openjdk:21-jdk-oracle

WORKDIR /app

COPY target/copsboot-*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]
