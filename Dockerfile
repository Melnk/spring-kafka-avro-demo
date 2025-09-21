FROM openjdk:17-jdk-slim
WORKDIR /app
# Изменяем путь - добавляем backend/
COPY backend/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]