FROM openjdk:8u322-jdk as build
WORKDIR /
ADD . .
# download dependencies
RUN ./gradlew bootJar
RUN mv /build/libs/linetownelection-0.0.1-SNAPSHOT.jar /linetownelection.jar

FROM openjdk:8-jre
WORKDIR /
COPY --from=build linetownelection.jar /opt/linetownelection.jar
EXPOSE 8080
CMD java $JAVA_OPTS -jar /opt/linetownelection.jar