FROM openjdk:11-jre-slim
ADD ./target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","user-service-0.0.1-SNAPSHOT.jar"]