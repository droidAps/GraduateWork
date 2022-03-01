FROM openjdk:11.0.13-slim
WORKDIR /app
COPY ./artifacts/ ./
EXPOSE 8080
