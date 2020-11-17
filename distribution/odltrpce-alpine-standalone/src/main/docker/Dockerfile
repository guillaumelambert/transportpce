# Base alpine with added packages needed for Opendaylight
FROM ${base.image.repo}

# Add tools needed for OpenDaylight
RUN apk update && apk --no-cache add bash unzip rsync nss

# -----------------------------------------------------
#ENV JAVA_HOME /usr/lib/jvm/java-1.11-openjdk
ENV JAVA_HOME /opt/java/openjdk
ENV ODL_HOME /opt/opendaylight
ENV TRPCE_REPO mvn:org.opendaylight.transportpce/odl-transportpce/${trpce.features.version}/xml/features

# copy ODL
COPY odl/karaf-${odl.karaf.version} $ODL_HOME
# copy apps dependencies
COPY system /tmp/system
RUN rsync -a --ignore-existing /tmp/system $ODL_HOME
# copy scripts
COPY opt /opt

# Add additonal repositories to boot repositories
RUN cp $ODL_HOME/etc/org.apache.karaf.features.cfg $ODL_HOME/etc/org.apache.karaf.features.cfg.orig
RUN sed -i -e "\|featuresRepositories|s|$|, $TRPCE_REPO|" $ODL_HOME/etc/org.apache.karaf.features.cfg
RUN sed -i -e /featuresBoot/d $ODL_HOME/etc/org.apache.karaf.features.cfg
#RUN echo featuresBoot=8ed6b938-844e-4e32-9757-313811989a9f,odl-restconf-all,odl-netconf-topology,features-mdsal,odl-transportpce >> $ODL_HOME/etc/org.apache.karaf.features.cfg
#RUN echo featuresBoot=8ed6b938-844e-4e32-9757-313811989a9f,odl-transportpce >> $ODL_HOME/etc/org.apache.karaf.features.cfg
RUN echo featuresBoot=31b9e40e-60ee-4b4c-b7ee-61ba2033c7e9,odl-transportpce >> $ODL_HOME/etc/org.apache.karaf.features.cfg

# Set start script

RUN chmod 755 $ODL_HOME/bin/startODL.sh
ENTRYPOINT $ODL_HOME/bin/startODL.sh
EXPOSE 2550 8181
