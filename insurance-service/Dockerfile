FROM openjdk:11
ADD /build/libs/insurance-service-*.jar /insurance-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/insurance-service.jar"]