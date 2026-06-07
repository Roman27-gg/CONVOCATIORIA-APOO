FROM amazoncorretto:21-alpine3.23-full
USER root
RUN apk update  && apk upgrade

WORKDIR /home/user_auth
RUN addgroup -S appgroup && adduser -S -G appgroup appgroup
COPY target/*.jar app.jar
COPY src/main/resources/application.yml config/aplication.yml
RUN chown -R appgroup:appgroup /home/user_auth

USER appgroup

HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1

ENTRYPOINT ["java","-jar","app.jar"]