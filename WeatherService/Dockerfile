FROM openjdk:8-jdk-alpine
EXPOSE 8081
RUN mkdir weatherService
COPY target/WeatherServiceApplication-0.0.1-SNAPSHOT.jar /weatherService
WORKDIR /weatherService
CMD ["java", "-jar", "WeatherServiceApplication-0.0.1-SNAPSHOT.jar"]