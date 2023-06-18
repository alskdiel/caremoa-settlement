FROM openjdk:17-jdk-alpine
COPY target/*SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app.jar"]

