FROM maven:3.8.4-openjdk-11 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11

COPY --from=builder /usr/src/app/target/DeltaSQC.jar /usr/app/DeltaSQC.jar

ENTRYPOINT ["java", "-cp", "/usr/app/DeltaSQC.jar", "pl.put.poznan.sqc.application.SpringApplication"]