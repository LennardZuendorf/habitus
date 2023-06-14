# Preparation steps
FROM eclipse-temurin:18 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/habitus/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME

# Copy all the files
COPY ./build.gradle ./gradlew ./gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
COPY ./src $APP_HOME/src/

# Build desirable JAR
RUN ./gradlew clean build -x test

FROM eclipse-temurin:18-jdk-alpine
WORKDIR /root/
COPY --from=BUILD_IMAGE '/root/dev/habitus/build/libs/habitus-2.1.0-SNAPSHOT.jar' '/app/habitus.jar'
EXPOSE 8080
CMD ["java","-jar","/app/habitus.jar"]