FROM maven:3.9-eclipse-temurin-24 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests flyway:clean flyway:migrate -Dflyway.cleanDisabled=false -Dskip.jooq.generation=true


FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
RUN mkdir /data

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]