FROM openjdk:11-jre-slim
ADD target/product-service-0.0.1-SNAPSHOT.jar product-service-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","product-service-0.0.1-SNAPSHOT.jar"]