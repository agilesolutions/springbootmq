FROM openjdk:latest
WORKDIR /opt/src
COPY target/demo.jar /opt/src/demo.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/src/demo.jar"]