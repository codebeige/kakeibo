FROM clojure:boot-2.7.1-alpine as boot

WORKDIR /usr/local/kakeibo/

COPY . ./
RUN boot build

FROM openjdk:jre-alpine

WORKDIR /usr/local/kakeibo/
COPY --from=boot /usr/local/kakeibo/builds/kakeibo-service.jar ./

EXPOSE 80

ENTRYPOINT ["java"]
CMD ["-jar", "kakeibo-service.jar"]
