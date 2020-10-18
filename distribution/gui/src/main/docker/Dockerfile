# Base alpine with added packages needed for Opendaylight
FROM ${base.image.repo}

ENV JAVA_HOME /opt/java/openjdk

COPY opt opt/

CMD [ "java","-jar","/opt/bin/trpce-gui.jar" ]