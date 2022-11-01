FROM openjdk:11-jre-slim
ADD ./target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","order-service-0.0.1-SNAPSHOT.jar"]