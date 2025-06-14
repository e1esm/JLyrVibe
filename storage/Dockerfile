FROM maven:3.8.3-openjdk-17 as builder

COPY . .

RUN mvn clean
RUN mvn package

FROM openjdk:17-jdk-slim

RUN apt-get update && \
    apt-get install -y ffmpeg && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder target/storage-0.0.1-SNAPSHOT.jar storage.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/storage.jar"]