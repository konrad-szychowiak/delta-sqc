FROM maven:3.5-jdk-8-alpine as builder

COPY pom.xml /app
COPY src /app/src

RUN mvn -f /app/pom.xml clean package

FROM java:8

COPY --from=builder /app/target/DeltaSQC.jar /app/DeltaSQC.jar

ENTRYPOINT ["java", "-cp", "/app/DeltaSQC.jar", "pl.put.poznan.sqc.application.SpringApplication"]