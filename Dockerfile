FROM amazoncorretto:11-al2-jdk as build
WORKDIR /
ADD . .
# download dependencies
RUN ./gradlew bootJar
RUN mv /build/libs/linetownelection-0.0.1-SNAPSHOT.jar /linetownelection.jar


FROM amazoncorretto:11
ENV SERVICE_NAME="my-service"

RUN addgroup --gid 1001 -S $SERVICE_NAME && \
    adduser -G $SERVICE_NAME --shell /bin/false --disabled-password -H --uid 1001 $SERVICE_NAME && \
    mkdir -p /var/log/$SERVICE_NAME && \
    chown $SERVICE_NAME:$SERVICE_NAME /var/log/$SERVICE_NAME
WORKDIR /
COPY --from=build linetownelection.jar /opt/linetownelection.jar
EXPOSE 8080
USER $SERVICE_NAME
CMD java $JAVA_OPTS -jar /opt/linetownelection.jar
