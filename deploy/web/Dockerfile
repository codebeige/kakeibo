FROM openjdk:jre-alpine

WORKDIR /usr/local/kakeibo/
COPY ./target/kakeibo-web.jar ./

ENTRYPOINT ["java"]
CMD ["-jar", "kakeibo-web.jar"]
