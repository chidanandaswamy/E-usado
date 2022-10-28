FROM openjdk:11
ADD ./target/eurekaServer-0.0.1-SNAPSHOT.jar /usr/src/eurekaServer-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "eurekaServer-0.0.1-SNAPSHOT.jar"]
