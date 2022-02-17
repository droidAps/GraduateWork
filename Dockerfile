FROM node:erbium-alpine3.12
WORKDIR /app
COPY ./gate-simulator .
EXPOSE 9999
