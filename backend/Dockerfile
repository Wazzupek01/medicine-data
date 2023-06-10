FROM eclipse-temurin:17-jdk-alpine
COPY ./ /
#RUN ./mvnw install -DskipTests
RUN ./mvnw package -DskipTests

ENTRYPOINT ["java", "-jar", "/target/medicine-data-0.0.1-SNAPSHOT.jar"]