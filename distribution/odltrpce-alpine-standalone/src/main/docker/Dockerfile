# Base odl alpine with added packages needed for
FROM ${base.image.repo}
MAINTAINER CCSDK Team (onap-ccsdk@lists.onap.org)
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV ODL_HOME /opt/opendaylight
ENV SDNC_CONFIG_DIR /opt/onap/ccsdk/data/properties

# copy CCSDK mvn artifacts to ODL repository
COPY system /tmp/system
RUN rsync -a /tmp/system $ODL_HOME && rm -rf /tmp/system
COPY opt /opt
RUN if [ ! -f $ODL_HOME/bin/configure_cluster.sh ] ; then echo "Fix cluster script" ; chmod 755 /opt/onap/ccsdk/bin/configure_cluster.sh ; mv /opt/onap/ccsdk/bin/configure_cluster.sh $ODL_HOME/bin ; fi
# RUN chmod 755 /opt/onap/ccsdk/bin/configure_cluster.sh
# RUN mv /opt/onap/ccsdk/bin/configure_cluster.sh $ODL_HOME/bin

# Add additonal repositories to boot repositories
RUN cp $ODL_HOME/etc/org.apache.karaf.features.cfg $ODL_HOME/etc/org.apache.karaf.features.cfg.orig
RUN sed /featuresBoot/d $ODL_HOME/etc/org.apache.karaf.features.cfg.orig > $ODL_HOME/etc/org.apache.karaf.features.cfg2
RUN cat $ODL_HOME/etc/org.apache.karaf.features.cfg2 | sed -e "\|featuresRepositories|s|$|, ${odl.repolist.features}|" > $ODL_HOME/etc/org.apache.karaf.features.cfg
RUN echo featuresBoot=config,standard,region,package,kar,ssh,management,odl-mdsal-all,odl-mdsal-apidocs,odl-daexim-all,odl-netconf-topology,odl-restconf-all >> $ODL_HOME/etc/org.apache.karaf.features.cfg

RUN chmod 755 /opt/onap/sdnc/bin/startODL.oom.sh
ENTRYPOINT /opt/onap/sdnc/bin/startODL.oom.sh
EXPOSE 2550 8181 8185 9300