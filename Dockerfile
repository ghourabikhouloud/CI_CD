FROM openjdk:17
FROM openjdk:17
EXPOSE 8082
ADD target/DevOps_Project-1.0.jar  DevOps_Project.jar
ENTRYPOINT ["java","-jar","DevOps_Project.jar"]
