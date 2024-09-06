FROM devops-tools.dtp.appgate.com:9443/base/alpine-jdk17:1.0.1
# use https://appgateinc.atlassian.net/wiki/spaces/DTP/pages/4151869475/Image+creation+guidelines
ARG BUILD_DATE
ARG BUILD_VERSION

ENV JAR={{project_name}}.jar

WORKDIR /app

USER user
COPY target/*.jar /app

ENV CONFIG_OPTS="--spring.config.additional-location=optional:file:./config/application.yml,optional:file:./secrets/application-secrets.yml"

CMD ["sh","-c","java $JAVA_OPTS -jar /app/${JAR} $CONFIG_OPTS"]