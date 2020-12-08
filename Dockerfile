FROM adoptopenjdk/openjdk9-openj9:latest

WORKDIR /app

COPY target/bip-employee-0.0.1-SNAPSHOT.jar /app/bip-employee.jar

ENTRYPOINT ["java","-jar","bip-employee.jar"]