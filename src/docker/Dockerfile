FROM fabric8/java-alpine-openjdk8-jre:1.5.4
WORKDIR /deployments
ENV TZ=America/Sao_Paulo \
    SPRING_PROFILES_ACTIVE="docker" \
    JAVA_OPTIONS="-Xmx256m" \
    CONTAINER_MAX_MEMORY=256m \
    JAVA_APP_NAME="training" \
    AB_OFF=true

ADD training-0.0.1-SNAPSHOT.jar /deployments