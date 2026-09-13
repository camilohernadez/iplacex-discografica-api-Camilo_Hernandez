FROM gradle:9.7.1-jdk21 AS builder

WORKDIR /app

COPY ./build.gradle .
COPY ./settings.gradle .

COPY src ./src 

RUN gradle build --no-daemon -x test

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar Discografia.jar

EXPOSE 8081

CMD [ "java", "-jar", "Discografia.jar" ]