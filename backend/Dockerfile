FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/medicine-data-0.0.1-SNAPSHOT.jar /medicine-data-0.0.1-SNAPSHOT.jar
COPY src/main/resources/data.xml /src/main/resources/data.xml
ENTRYPOINT ["java", "-jar", "/medicine-data-0.0.1-SNAPSHOT.jar"]