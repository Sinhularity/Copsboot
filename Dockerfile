FROM openjdk:21-jdk-oracle
WORKDIR /app
COPY target/Copsboot.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
