FROM fabric8/java-alpine-openjdk8-jre:1.5.4
WORKDIR /deployments
ENV TZ=America/Sao_Paulo \
    SPRING_PROFILES_ACTIVE="docker" \
    JAVA_OPTIONS="-Xmx256m" \
    CONTAINER_MAX_MEMORY=256m \
    JAVA_APP_NAME="training" \
    AB_OFF=true
RUN wget -O apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.6.0/elastic-apm-agent-1.6.0.jar
ADD training-0.0.1-SNAPSHOT.jar /deployments
CMD java -javaagent:/deployments/apm-agent.jar \
      -Delastic.apm.service_name=training \
      -Delastic.apm.application_packages=com.dng.api \
      -Delastic.apm.server_url=http://apm:8200 \
      -jar training-0.0.1-SNAPSHOT.jar