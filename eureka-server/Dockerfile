FROM openjdk:11-jre-slim
ADD ./target/eurekaServer-0.0.1-SNAPSHOT.jar eurekaServer-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar", "eurekaServer-0.0.1-SNAPSHOT.jar"]
