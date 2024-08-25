FROM amazoncorretto:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /rankings.jar

COPY setup.sh /setup.sh

RUN chmod +x /setup.sh

ENTRYPOINT ["/setup.sh"]