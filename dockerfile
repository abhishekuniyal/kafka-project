FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/kafka-project-0.0.1-SNAPSHOT.jar kafka-project-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kafka-project-0.0.1.jar"]
