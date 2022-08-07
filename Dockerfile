FROM amazoncorretto:11.0.15
RUN mkdir src
COPY . /tmp
WORKDIR /tmp
RUN sed -i -e 's/\r$//' mvnw
RUN chmod 700 mvnw && ./mvnw clean install package -DskipTests && ls target
RUN cp target/*.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
