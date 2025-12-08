FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY ../../gradlew .
COPY ../../build.gradle.kts .
COPY ../../settings.gradle.kts .
COPY ./ ./wallet-app/

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/wallet-app/build/libs/wallet-app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]