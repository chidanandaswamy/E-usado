FROM openjdk:11
ADD ./target/authentication-service-0.0.1-SNAPSHOT.jar /usr/src/authentication-service-0.0.1-SNAPSHOT.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar", "authentication-service-0.0.1-SNAPSHOT.jar"]


# FROM openjdk:11-jre-slim
# ADD target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
# EXPOSE 8091
# ENTRYPOINT ["java","-jar","user-service-0.0.1-SNAPSHOT.jar"]