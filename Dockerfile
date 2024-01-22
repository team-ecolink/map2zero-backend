FROM azul/zulu-openjdk-alpine:17-latest

WORKDIR /app

ARG JAR_PATH=./build/libs

ENV ACTIVE_PROFILE 'dev'

COPY ${JAR_PATH}/*.jar ${JAR_PATH}/*.jar

EXPOSE 80

CMD ["java","-jar","-Dspring.profiles.active=${ACTIVE_PROFILE}","./build/libs/*.jar"]
