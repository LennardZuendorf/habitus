FROM eclipse-temurin:18-alpine
WORKDIR /app
COPY build/libs/habitus-2.1.0.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","habitus-2.1.0.jar"]