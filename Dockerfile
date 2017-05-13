FROM clojure:boot-2.7.1-alpine

WORKDIR /usr/local/kakeibo/

VOLUME /root/.m2/
VOLUME /usr/local/kakeibo/

EXPOSE 80

ENTRYPOINT ["boot"]
CMD ["run"]
