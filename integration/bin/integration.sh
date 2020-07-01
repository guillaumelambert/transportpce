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

DIRBIN="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
DIRCUR="${PWD##*/}"
if [ ! -z "$2" ]; then
    PREFIX="$2"
else
    PREFIX=$(echo $DIRCUR | sed -e "s/-//g" | sed -e "s/ /_/g")
fi
case "$1" in

    "info")
        show_info "$PREFIX"
    ;;

esac;
