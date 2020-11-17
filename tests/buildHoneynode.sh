#!/bin/sh

ORDMVERSION=${1:-1.2.1}

#set -e
#. $(dirname $0)/reflectwarn.sh
#cd honeynode/$ORDMVERSION
mvn clean install -DskipTests -Dcheckstyle.skip -Dmaven.javadoc.skip=true -s honeynode/fd_io_honeycomb_settings.xml -f honeynode/$ORDMVERSION
chmod +x ./honeynode/$ORDMVERSION/honeynode-distribution/target/honeynode-distribution-1.19.04-hc/honeynode-distribution-1.19.04/honeycomb-tpce
exit $?
