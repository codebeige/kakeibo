FROM openjdk:jre-alpine

WORKDIR /usr/local/kakeibo/
COPY ./builds/kakeibo-service.jar ./

ENTRYPOINT ["java"]
CMD ["-jar", "kakeibo-service.jar"]
