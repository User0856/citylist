FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9-slim
ADD build/libs/citylist-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]