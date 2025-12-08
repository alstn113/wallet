FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY ../../gradlew .
COPY ../../build.gradle.kts .
COPY ../../settings.gradle.kts .
COPY ./ ./transfer-app/

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/transfer-app/build/libs/transfer-app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]