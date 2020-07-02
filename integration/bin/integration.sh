#!/bin/bash

function pad_right(){
    ten="            " 
    forty="$ten$ten"
    y="${1:0:24}${forty:0:$((24 - ${#1}))}"
    echo -n "${y}"
}
function show_info_header(){
    pad_right "container"
    pad_right "ip" 
    echo "running"
    echo "=================================================================="
    
}
function show_info_for(){
    CONTAINER_NAME=$1
    info=$(docker inspect $CONTAINER_NAME)
    re="\"IPAddress\": \"([0-9]+\.[0-9]+\.[0-9]+\.[0-9]+)\""
    if [[ "$info" =~ $re ]]; then
        IP="${BASH_REMATCH[1]}"; 
    fi
    re="\"State\": \{.*\"Running\": (false|true)"
    if [[ "$info" =~ $re ]]; then
        STATUS="${BASH_REMATCH[1]}"; 
    fi
    pad_right $CONTAINER_NAME
    pad_right $IP 
    echo "$STATUS"
}

function show_info(){
    PRE=$1
    show_info_header
    show_info_for $PRE"_sdnr_1"
    show_info_for $PRE"_sdnrdb_1"
    show_info_for $PRE"_transportpce_1"
    show_info_for $PRE"_sdncweb_1"
    show_info_for $PRE"_roadma_1"
    show_info_for $PRE"_roadmb_1"
    show_info_for $PRE"_roadmc_1"
    show_info_for $PRE"_xpdra_1"
    show_info_for $PRE"_xpdrc_1"
}


function do_mount(){
    SDNRNAME="$1"
    SIMNAME="$2"
    SIMUSERNAME="$3"
    SIMPASSWORD="$4"

    #get ip address of sdnr
    info=$(docker inspect $SDNRNAME)
    re="\"IPAddress\": \"([0-9]+\.[0-9]+\.[0-9]+\.[0-9]+)\""
    if [[ "$info" =~ $re ]]; then
        SDNRIP="${BASH_REMATCH[1]}"; 
    else
        echo "unable to mount $SIMNAME. sdnr not found"
        return
    fi
    info=$(docker inspect $SIMNAME)
    if [[ "$info" =~ $re ]]; then
        SIMIP="${BASH_REMATCH[1]}"; 
    else
        echo "unable to mount $SIMNAME. sim not found"
        return
    fi
    #clean up simname (no underscores or so)
    SIMNAME=$(echo $SIMNAME | sed -e "s/-//g" | sed -e "s/_//g")
    URL="http://$SDNRIP:8181/rests/data/network-topology:network-topology/topology=topology-netconf"
    #execute curl command
    echo "try to mount $SIMIP:$SIMPORT with name $SIMNAME to $SDNRIP"
    curl -X POST -u "$SDNR_USERNAME":"$SDNR_PASSWORD" \
    -H "Content-Type: application/yang-data+json" -sf \
    --data @<(cat <<EOF
  {
    "node": {
        "node-id": "$SIMNAME",
        "netconf-node-topology:port": $SIMPORT,
        "netconf-node-topology:host": "$SIMIP",
        "netconf-node-topology:username": "$SIMUSERNAME",
        "netconf-node-topology:password": "$SIMPASSWORD",
        "netconf-node-topology:tcp-only": false,
        "netconf-node-topology:reconnect-on-changed-schema": false,
        "netconf-node-topology:connection-timeout-millis": 20000,
        "netconf-node-topology:max-connection-attempts": 100,
        "netconf-node-topology:between-attempts-timeout-millis": 2000,
        "netconf-node-topology:sleep-factor": 1.5,
        "netconf-node-topology:keepalive-delay": 120
    }
}
EOF
    ) \
    "$URL"
}
function do_unmount(){
    SDNRNAME="$1"
    SIMNAME="$2"
    
    #get ip address of sdnr
    info=$(docker inspect $SDNRNAME)
    re="\"IPAddress\": \"([0-9]+\.[0-9]+\.[0-9]+\.[0-9]+)\""
    if [[ "$info" =~ $re ]]; then
        SDNRIP="${BASH_REMATCH[1]}"; 
    else
        echo "unable to unmount $SIMNAME. sdnr not found"
        return
    fi
    #clean up simname (no underscores or so)
    SIMNAME=$(echo $SIMNAME | sed -e "s/-//g" | sed -e "s/_//g")
    URL="http://$SDNRIP:8181/rests/data/network-topology:network-topology/topology=topology-netconf/node=$SIMNAME"
    #execute curl command
    curl -X DELETE -u "$SDNR_USERNAME":"$SDNR_PASSWORD" "$URL"
}

function mount_all(){
    PRE=$1
    do_mount $PRE"_sdnr_1" $PRE"_roadma_1" "admin" "admin"
    do_mount $PRE"_sdnr_1" $PRE"_roadmb_1" "admin" "admin"
    do_mount $PRE"_sdnr_1" $PRE"_roadmc_1" "admin" "admin"
    do_mount $PRE"_sdnr_1" $PRE"_xpdra_1" "admin" "admin"
    do_mount $PRE"_sdnr_1" $PRE"_xpdrc_1" "admin" "admin"
}
function unmount_all(){
    PRE=$1
    do_unmount $PRE"_sdnr_1" $PRE"_roadma_1"
    do_unmount $PRE"_sdnr_1" $PRE"_roadmb_1"
    do_unmount $PRE"_sdnr_1" $PRE"_roadmc_1"
    do_unmount $PRE"_sdnr_1" $PRE"_xpdra_1"
    do_unmount $PRE"_sdnr_1" $PRE"_xpdrc_1"
}
function print_help(){
    echo "integration script for transportPCE and ONAP"
    echo "============================================"
    echo "usage:"
    echo "  * please use it inside your docker-compose folder you have started. otherwise add PREFIX argument"
    echo " ../bin/integration.sh COMMAND [PREFIX]"
    echo "commands:"
    echo "  * info    : show ip and status of containers"
    echo "  * mount   : mount sims to sdnr container"
    echo "  * unmount : unmount sims from sdnr container"
    echo ""
}

DIRBIN="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
DIRCUR="${PWD##*/}"

source $(pwd)/.env

if [ ! -z "$2" ]; then
    PREFIX="$2"
else
    PREFIX=$(echo $DIRCUR | sed -e "s/-//g" | sed -e "s/ /_/g")
fi
case "$1" in

    "info")
        show_info "$PREFIX"
    ;;
    "mount")
        mount_all "$PREFIX"
    ;;
    "unmount")
        unmount_all "$PREFIX"
    ;;
    *)
        print_help
        exit 1
    ;;


esac;
