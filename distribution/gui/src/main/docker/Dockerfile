# Base alpine with added packages needed for Opendaylight
FROM ${base.image.repo}

ENV JAVA_HOME /opt/java/openjdk
ENV IETF_NETWORK_BASEURL http://transportpce:8181
ENV TRANSPORTPCE_BASEURL http://transportpce:8181

COPY opt opt/

CMD [ "java","-jar","/opt/bin/trpce-gui.jar" ]