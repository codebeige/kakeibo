FROM openjdk:jre-alpine

WORKDIR /usr/local/kakeibo/
COPY ./target/kakeibo-service.jar ./

ENTRYPOINT ["java"]
CMD ["-jar", "kakeibo-service.jar"]
