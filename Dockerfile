# Build stage
FROM openjdk:8

# Build stage
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . .
