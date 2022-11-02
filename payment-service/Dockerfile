FROM openjdk:11-jre-slim
ADD ./target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","payment-service-0.0.1-SNAPSHOT.jar"]