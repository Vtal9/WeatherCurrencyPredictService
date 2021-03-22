FROM openjdk:8-jdk-alpine
EXPOSE 8761
RUN mkdir eurekaServer
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar /eurekaServer
WORKDIR /eurekaServer
CMD ["java", "-jar", "eurekaserver-0.0.1-SNAPSHOT.jar"]