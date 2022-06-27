FROM openjdk:18-jdk-alpine
COPY build/libs/MilesPay-*.jar MilesPay.jar
ENTRYPOINT ["java","-jar","/MilesPay.jar"]
