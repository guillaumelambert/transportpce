#!/bin/bash

SIM_IP="localhost"
declare -a SIMPORTS=(10000 10001 10002 10003 10004)
declare -a NEMODELS=(
  "oper-ROADMA-full.xml"
  "oper-ROADMB.xml"
  "oper-ROADMC-full.xml"
  "oper-XPDRA.xml"
  "oper-XPDRC.xml"
)
declare -a NAMES=(
  "roadma-1-full"
  "roadmb-1"
  "roadmc-1-full"
  "xpdra-1"
  "xpdrc-1"
)
NEMODELS_PATH="sample_configs/openroadm/"
YANG_PATH="NetconfServerSimulator/yang/yangNeModel"
SIM_BIN="java -jar NetconfServerSimulator/build/NetconfServerSimulator.jar "
SIM_LOGPATH="logs/"
#params: 
#     NetconfServerSimulator/xmlNeModel/DVM_MWCore12_BasicAir.xml 
#     10001 
#     NetconfServerSimutor/yang/yangNeModel"
MOUNT_HEADERS_1="Content-Type: application/json" 
MOUNT_USERNAMEPASSWORD="admin:admin"
#BASEURL="http://172.18.0.3:8181/"
BASEURL="http://localhost:8181/"
NE_IP="192.168.178.169"
declare -a PIDS=()
ODL_LOGFILE="/opt/opendaylight/data/log/karaf.log"
ODL_DOCKER_NAME="sdnr"
PRINT_LOG_COMMAND="docker exec -ti $DOCKER_NAME cat $ODL_LOGFILE"
STEPBYSTEP=0
if [ "$1" == "--single" ]; then
  STEPBYSTEP=1
fi
start_sims()
{
  if [ ! -d "$SIM_LOGPATH" ]; then
    mkdir -p $SIM_LOGPATH
  fi
  for i in  ${!SIMPORTS[@]};
  do
    p=${SIMPORTS[i]}
    ne=$NEMODELS_PATH${NEMODELS[i]}
    log=$(basename -- "$ne")
    $SIM_BIN $ne $p $YANG_PATH >$SIM_LOGPATH$log".log" 2>&1 &
    PIDS[$i]=$!
    echo "started simulator with ne=$ne and pid=${PIDS[i]}"
  done

}
stop_sims()
{
  for pid in  ${PIDS[@]};
  do
    kill $pid
    echo "stopped simulator with pid=$pid"
  done
}
mount_sim()
{
  METHOD="PUT"
  cmd="mounting"
  if [ "$1" == "d" ]; then
    METHOD="DELETE"
    cmd="unounting"
  fi
    p=$2
    ne=$3
    name=$4
#    name="$(cut -d'.' -f1 <<<"$ne")"
    URL=$BASEURL"restconf/config/network-topology:network-topology/topology/topology-netconf/node/"$name


    DATA=$(cat <<-END
 {
    "node": [
        {
            "node-id": "$name",
            "netconf-node-topology:tcp-only": "false",
            "netconf-node-topology:reconnect-on-changed-schema": "false",
            "netconf-node-topology:host": "$NE_IP",
            "netconf-node-topology:default-request-timeout-millis": "120000",
            "netconf-node-topology:max-connection-attempts": "0",
            "netconf-node-topology:sleep-factor": "1.5",
            "netconf-node-topology:actor-response-wait-time": "5",
            "netconf-node-topology:concurrent-rpc-limit": "0",
            "netconf-node-topology:between-attempts-timeout-millis": "2000",
            "netconf-node-topology:port": "$p",
            "netconf-node-topology:connection-timeout-millis": "20000",
            "netconf-node-topology:username": "admin",
            "netconf-node-topology:password": "admin",
            "netconf-node-topology:keepalive-delay": "300"
        }
    ]
}
END
)
  DATA=$(echo $DATA | paste -s)
  echo "$cmd $name"
  curl -X $METHOD -H "$MOUNT_HEADERS_1" -u "$MOUNT_USERNAMEPASSWORD" --data "$DATA" $URL

}

mount_sims()
{
  c="c"
  if [ "$1" == "d" ]; then
    c="d"
  fi
  for i in  ${!SIMPORTS[@]};
  do
    p=${SIMPORTS[i]}
    ne=${NEMODELS[i]}
    name=${NAMES[i]}
    mount_sim $c $p $ne $name
    if [ $STEPBYSTEP -eq 1 ];then
      read -p "Press enter to continue"
    fi
  done
}
link_roadm_roadm()
{
  fromId=$1   #string
  fromEp=$2   #string
  fromDeg=$3  #uint8
  toId=$4     #string
  toEp=$5     #string
  toDeg=$6    #uint8
  echo "try to create link from $fromId(ep=$fromEp,deg=$fromDeg) to $toId(ep=$toEp,deg=$toDeg)"
  URL=$BASEURL"restconf/operations/transportpce-networkutils:init-roadm-nodes"
  DATA=$(cat <<-END
  {
  "transportpce-networkutils:input": {
    "transportpce-networkutils:rdm-a-node": "$fromId",
    "transportpce-networkutils:deg-a-num": "$fromDeg",
    "transportpce-networkutils:termination-point-a": "$fromEp",
    "transportpce-networkutils:rdm-z-node": "$toId",
    "transportpce-networkutils:deg-z-num": "$toDeg",
    "transportpce-networkutils:termination-point-z": "$toEp"
  }
}
END
)
  curl -X POST -H "$MOUNT_HEADERS_1" -u "$MOUNT_USERNAMEPASSWORD" --data "$DATA" $URL
  if [ $STEPBYSTEP -eq 1 ];then
    read -p "Press enter to continue"
  fi
}
link_xpdr_roadm()
{
  xpId=$1         #string
  xpPort=$2       #uint8
  xpNetNo=$3      #unit8
  rdmId=$4        #string
  srgNo=$5        #unit8
  logConPoint=$6  #string

  echo "try to create link from xpdr $xpId(port=$xpPort,netNo=$xpNetNo) to roadm $rmdId(srgNo=$srgNo,cp=$logConPoint)"
  URL=$BASEURL"restconf/operations/transportpce-networkutils:init-xpdr-rdm-links"
  DATA=$(cat <<-END
  {
  "transportpce-networkutils:input": {
    "transportpce-networkutils:links-input": {
      "transportpce-networkutils:xpdr-node": "$xpId",
      "transportpce-networkutils:xpdr-num": "1",
      "networkutils:network-num": "$xpNetNo",
      "transportpce-networkutils:network-num": "$xpPort",
      "transportpce-networkutils:rdm-node": "$rdmId",
      "transportpce-networkutils:srg-num": "$srgNo",
      "transportpce-networkutils:termination-point-num": "$logConPoint"
    }
  }
}
END
)
  curl -X POST -H "$MOUNT_HEADERS_1" -u "$MOUNT_USERNAMEPASSWORD" --data "$DATA" $URL
  if [ $STEPBYSTEP -eq 1 ];then
    read -p "Press enter to continue"
  fi
}

link_roadm_xpdr()
{
  xpId=$1         #string
  xpPort=$2       #uint8
  xpNetNo=$3      #unit8
  rdmId=$4        #string
  srgNo=$5        #unit8
  logConPoint=$6  #string

  echo "try to create link from roadm $rmdId(srgNo=$srgNo,cp=$logConPoint) to xpdr $xpId(port=$xpPort,netNo=$xpNetNo)"
  URL=$BASEURL"restconf/operations/transportpce-networkutils:init-rdm-xpdr-links"
  DATA=$(cat <<-END
  {
  "transportpce-networkutils:input": {
    "transportpce-networkutils:links-input": {
      "transportpce-networkutils:xpdr-node": "$xpId",
      "transportpce-networkutils:xpdr-num": "1",
      "networkutils:network-num": "$xpNetNo",
      "transportpce-networkutils:network-num": "$xpPort",
      "transportpce-networkutils:rdm-node": "$rdmId",
      "transportpce-networkutils:srg-num": "$srgNo",
      "transportpce-networkutils:termination-point-num": "$logConPoint"
    }
  }
}
END
)
  curl -X POST -H "$MOUNT_HEADERS_1" -u "$MOUNT_USERNAMEPASSWORD" --data "$DATA" $URL
  if [ $STEPBYSTEP -eq 1 ];then
    read -p "Press enter to continue"
  fi
}

create_service()
{
  xpdra=$1
  xpdra_ccli=$2
  xpdrz=$3
  xpdrz_ccli=$4


  URL=$BASEURL"restconf/operations/org-openroadm-service:service-create"
  DATA=$(cat <<-END
 {
    "org-openroadm-service:input": {
        "org-openroadm-service:service-name": "service-1",
        "org-openroadm-service:connection-type": "service",
        "org-openroadm-service:sdnc-request-header": {
            "org-openroadm-service:request-id": "request-1",
            "org-openroadm-service:rpc-action": "service-create"
        },
        "org-openroadm-service:service-a-end": {
            "org-openroadm-service:node-id": "$xpdra",
            "org-openroadm-service:service-rate": "1",
            "org-openroadm-service:service-format": "Ethernet",
            "org-openroadm-service:clli": "$xpdra_ccli",
            "org-openroadm-service:tx-direction": {
                "org-openroadm-service:port": {
                    "org-openroadm-service:port-device-name": "$xpdra-device-name",
                    "org-openroadm-service:port-type": "$xpdra-port-type",
                    "org-openroadm-service:port-name": "$xpdra-port-name",
                    "org-openroadm-service:port-rack": "$xpdra-port-rack",
                    "org-openroadm-service:port-shelf": "$xpdra-port-shelf",
                    "org-openroadm-service:port-slot": "$xpdra-port-slot",
                    "org-openroadm-service:port-sub-slot": "$xpdra-port-sub-plot"
                },
                "org-openroadm-service:lgx": {
                  "org-openroadm-service:lgx-device-name": "$xpdra-lgx-device-name",
                  "org-openroadm-service:lgx-port-name": "$xpdra-lgx-port-name",
                  "org-openroadm-service:lgx-port-rack": "$xpdra-lgx-port-rack",
                  "org-openroadm-service:lgx-port-shelf": "$xpdra-lgx-port-shelf"
                }
            },
            "org-openroadm-service:rx-direction": {
                "org-openroadm-service:port": {
                    "org-openroadm-service:port-device-name": "$xpdra-device-name",
                    "org-openroadm-service:port-type": "$xpdra-port-type",
                    "org-openroadm-service:port-name": "$xpdra-port-name",
                    "org-openroadm-service:port-rack": "$xpdra-port-rack",
                    "org-openroadm-service:port-shelf": "$xpdra-port-shelf",
                    "org-openroadm-service:port-slot": "$xpdra-port-slot",
                    "org-openroadm-service:port-sub-slot": "$xpdra-port-sub-plot"
                },
                "org-openroadm-service:lgx": {
                  "org-openroadm-service:lgx-device-name": "$xpdra-lgx-device-name",
                  "org-openroadm-service:lgx-port-name": "$xpdra-lgx-port-name",
                  "org-openroadm-service:lgx-port-rack": "$xpdra-lgx-port-rack",
                  "org-openroadm-service:lgx-port-shelf": "$xpdra-lgx-port-shelf"
                }
            }
        },
        "org-openroadm-service:service-z-end": {
           "org-openroadm-service:node-id": "$xpdrz",
            "org-openroadm-service:service-rate": "1",
            "org-openroadm-service:service-format": "Ethernet",
            "org-openroadm-service:clli": "$xpdrz_ccli",
            "org-openroadm-service:tx-direction": {
                "org-openroadm-service:port": {
                    "org-openroadm-service:port-device-name": "$xpdrz-device-name",
                    "org-openroadm-service:port-type": "$xpdrz-port-type",
                    "org-openroadm-service:port-name": "$xpdrz-port-name",
                    "org-openroadm-service:port-rack": "$xpdrz-port-rack",
                    "org-openroadm-service:port-shelf": "$xpdrz-port-shelf",
                    "org-openroadm-service:port-slot": "$xpdrz-port-slot",
                    "org-openroadm-service:port-sub-slot": "$xpdrz-port-sub-plot"
                },
                "org-openroadm-service:lgx": {
                  "org-openroadm-service:lgx-device-name": "$xpdrz-lgx-device-name",
                  "org-openroadm-service:lgx-port-name": "$xpdrz-lgx-port-name",
                  "org-openroadm-service:lgx-port-rack": "$xpdrz-lgx-port-rack",
                  "org-openroadm-service:lgx-port-shelf": "$xpdrz-lgx-port-shelf"
                }
            },
            "org-openroadm-service:rx-direction": {
                "org-openroadm-service:port": {
                    "org-openroadm-service:port-device-name": "$xpdrz-device-name",
                    "org-openroadm-service:port-type": "$xpdrz-port-type",
                    "org-openroadm-service:port-name": "$xpdrz-port-name",
                    "org-openroadm-service:port-rack": "$xpdrz-port-rack",
                    "org-openroadm-service:port-shelf": "$xpdrz-port-shelf",
                    "org-openroadm-service:port-slot": "$xpdrz-port-slot",
                    "org-openroadm-service:port-sub-slot": "$xpdrz-port-sub-plot"
                },
                "org-openroadm-service:lgx": {
                  "org-openroadm-service:lgx-device-name": "$xpdrz-lgx-device-name",
                  "org-openroadm-service:lgx-port-name": "$xpdrz-lgx-port-name",
                  "org-openroadm-service:lgx-port-rack": "$xpdrz-lgx-port-rack",
                  "org-openroadm-service:lgx-port-shelf": "$xpdrz-lgx-port-shelf"
                }
            }
        }
    }
}
END
)
  curl -X POST -H "$MOUNT_HEADERS_1" -u "$MOUNT_USERNAMEPASSWORD" --data "$DATA" $URL
  if [ $STEPBYSTEP -eq 1 ];then
    read -p "Press enter to continue"
  fi
}


RESULT="not even started"

echo "starting integration test for transportpce"
echo "=========================================="
echo ""
echo "starting simulators..."
start_sims
sleep 10
echo "done"
RESULT="simulators started"
echo "mounting simulators..."
#mount_sim c 10000 "oper-ROADMA-full.xml"
mount_sims
sleep 10
echo "done"
RESULT="simulators mounted"
echo "create links roadm to roadm..."
link_roadm_roadm ${NAMES[0]} "DEG1-CTP-TXRX" "0" ${NAMES[1]} "DEG1-CTP-TXRX" "0" #link roadma <-> roadmb
link_roadm_roadm ${NAMES[1]} "DEG1-CTP-TXRX" "0" ${NAMES[2]} "DEG1-CTP-TXRX" "0" #link roadmb <-> roadmc
echo "create links roadm to xpdrs..."
link_xpdr_roadm ${NAMES[3]} "1" "1" ${NAMES[0]} "1" "DEG1-CTP-TXRX"     #link xpdra -> roadma
link_roadm_xpdr ${NAMES[4]} "1" "1" ${NAMES[2]} "1" "DEG1-CTP-TXRX"     #link roadmc -> xpdrc
echo "done"
echo "create service"
create_service "NodeA" ${NAMES[3]} "NodeB" ${NAMES[4]}
echo "done"
read -p "Test finished? Press enter to continue"

echo -n "unmounting simulators..."
mount_sims d
echo "done"
sleep 5
echo "stopping simulators..."
stop_sims

echo "done"


echo ""
echo "finished $RESULT"
