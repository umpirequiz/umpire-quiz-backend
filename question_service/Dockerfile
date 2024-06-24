FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal

COPY --chown=1001:0 /src/main/liberty/config /config
COPY --chown=1001:0 /target/lib/*.jar /opt/ol/wlp/usr/shared/resources
RUN features.sh

COPY --chown=1001:0 /target/importer_service.war /config/apps
RUN configure.sh