<<<<<<< HEAD
FROM maven:3.6.3-jdk-11-slim AS builder
ARG GIT_REF=master
RUN apt-get update \
    && apt-get -f -m install -y git \
    && rm -rf /var/lib/apt/lists/* \
    && mkdir /tmp/git_repo \
    && git clone -b "$GIT_REF"  https://git.opendaylight.org/gerrit/transportpce /tmp/git_repo \
    && cd /tmp/git_repo/tests \
    && ./buildHoneynode.sh  \
    && ./buildHoneynode.sh 2.2.1 \
    && mkdir -p /tmp/honeynode/honeynode_1.2.1/config \
    && mkdir -p /tmp/honeynode/honeynode_2.2.1/config \
    && mv /tmp/git_repo/tests/honeynode/1.2.1/honeynode-distribution/target/honeynode-distribution-1.19.04-hc/honeynode-distribution-1.19.04/* /tmp/honeynode/honeynode_1.2.1/. \
    && mv /tmp/git_repo/tests/honeynode/2.2.1/honeynode-distribution/target/honeynode-distribution-1.19.04-hc/honeynode-distribution-1.19.04/* /tmp/honeynode/honeynode_2.2.1/. \
    && cp /tmp/git_repo/tests/sample_configs/openroadm/1.2.1/* /tmp/honeynode/honeynode_1.2.1/config/. \
    && cp /tmp/git_repo/tests/sample_configs/openroadm/2.2.1/* /tmp/honeynode/honeynode_2.2.1/config/.
=======
FROM alpine/git:latest AS builder
RUN mkdir -p /tmp/git_repo \
    && mkdir -p /tmp/artifacts/1.2.1 \
    && mkdir -p /tmp/artifacts/2.2.1 \
    && mkdir -p /tmp/honeynode/1.2.1 \
    && mkdir -p /tmp/honeynode/2.2.1 \
    && mkdir -p /tmp/sample_configs/ \
    && git clone  https://gitlab.com/Orange-OpenSource/lfn/odl/honeynode-simulator.git /tmp/git_repo \
    && cd /tmp/git_repo \
    && git checkout  honeynode-plugin-aggregator-1.0.1 \
    && mv /tmp/git_repo/sample_configs/* /tmp/sample_configs/. \
    && git checkout  honeynode-plugin-aggregator-2.0.0 \
    && mv /tmp/git_repo/sample_configs/* /tmp/sample_configs/. \
    && wget "https://gitlab.com/api/v4/projects/17518226/jobs/artifacts/honeynode-plugin-aggregator-1.0.0/download?job=mvn-build" -O /tmp/artifacts/1.2.1/artifact.zip \
    && unzip /tmp/artifacts/1.2.1/artifact.zip -d /tmp/artifacts/1.2.1 \
    && mv  /tmp/artifacts/1.2.1/honeynode-simulator/* /tmp/honeynode/1.2.1/. \
    && wget "https://gitlab.com/api/v4/projects/17518226/jobs/artifacts/honeynode-plugin-aggregator-2.0.0/download?job=mvn-build" -O /tmp/artifacts/2.2.1/artifact.zip \
    && unzip /tmp/artifacts/2.2.1/artifact.zip -d /tmp/artifacts/2.2.1 \
    && mv  /tmp/artifacts/2.2.1/honeynode-simulator/* /tmp/honeynode/2.2.1/.
>>>>>>> standalone/stable/aluminium




FROM adoptopenjdk/openjdk11:alpine-jre
LABEL Name="OpenROADM compliant ROADM and XPDR NETCONF simulators"
LABEL Description="Simulators for Openroadm devices based on honeycomb project"
LABEL Maintainer="transportpce-dev@lists.opendaylight.org"
LABEL Url="https://git.opendaylight.org/gerrit/transportpce"

ENV DEVICE_VERSION=1.2.1
ENV DEVICE_FILE=1.2.1/oper-ROADMA-full.xml
RUN addgroup gsimulator && adduser -D usimulator -G gsimulator -u 100001\
  && mkdir -p /opt/application/simulators
COPY --chown=usimulator:gsimulator --from=builder /tmp/honeynode/ /opt/application/simulators/honeynode
<<<<<<< HEAD
COPY --chown=usimulator:gsimulator --from=builder /tmp/git_repo/tests/sample_configs/openroadm/ /opt/application/simulators/honeynode/config
RUN find /opt/application/simulators -name "honeycomb-tpce" -exec  sed -i "/reflectwarn.sh/d" {} +
=======
COPY --chown=usimulator:gsimulator --from=builder /tmp/sample_configs/ /opt/application/simulators/honeynode/config
>>>>>>> standalone/stable/aluminium
USER usimulator
WORKDIR /opt/application/simulators/honeynode
HEALTHCHECK CMD wget --header "Authorization: Basic `echo -n "${USER}:${PASSWORD}" | base64`"  http://localhost:8130/restconf/ -S -O -
EXPOSE 1830/tcp 8130/tcp
<<<<<<< HEAD
CMD /opt/application/simulators/honeynode/honeynode_$DEVICE_VERSION/honeycomb-tpce 1830 /opt/application/simulators/honeynode/config/$DEVICE_FILE
=======
CMD /opt/application/simulators/honeynode/$DEVICE_VERSION/honeycomb-tpce 1830 /opt/application/simulators/honeynode/config/$DEVICE_FILE
>>>>>>> standalone/stable/aluminium
