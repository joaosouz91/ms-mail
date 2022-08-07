FROM amazoncorretto:11.0.15
COPY ./target /tmp
WORKDIR /tmp
RUN cp *.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar