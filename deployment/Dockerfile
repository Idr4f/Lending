FROM openjdk:19-ea-1-jdk-slim
COPY "./Lending.jar" "lending-back.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lending-back.jar"]