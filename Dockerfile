FROM amazoncorretto:11.0.15
COPY target/*.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar