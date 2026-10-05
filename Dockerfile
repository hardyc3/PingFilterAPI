FROM maven:3.9-eclipse-temurin-24 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -Dskip.jooq.generation=true

FROM eclipse-temurin:24-jre
WORKDIR /app
RUN mkdir /data
COPY --from=builder /app/target/PingFilterAPI-1.0.jar app.jar

EXPOSE 8000 5005
ENTRYPOINT ["java", "-jar", "app.jar"]