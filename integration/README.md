# Integration for TransportPCE to ONAP

This folder contains integration szenarios for transportPCE as a service in a absolute minimal ONAP environment. 


 * [Basic ONAP SDNC](onap/README.md)
 * [ONAP SDNC with NetconfServerSimulators](onap-ext/README.md)
 * [ONAP SDNC with HoneyNode Sims](onap-ext2/README.md)
 * [ONAP SDNC with NTSim Sims](onap-ext3/README.md)
 
Up to now it is for running a manual integration test to see if a standalone transportPCE instance is behaving the same like a remote one.

## Simulators

We have three different options for simulators:

 * NetconfserverSimulator https://github.com/Melacon/WirelessTransportEmulator
 * Honeynode based Simulator
 * NTSimulator https://github.com/Melacon/ntsim

## Preparation

 * compile all sources (maybe with skipTests): mvn clean install
 * build transportpce docker image

## Integration script

The integration script is located in the bin folder and called ```integration.py```. It has to be called from inside of the integration folder you like to execute(e.g. onap-ext) because its docker-compose naming is related to the subfolder. This script automaticall detect the IPs of the images and will do e.g. a mount with the correct parameters, so that you don't have to care about all these things. I don't wanted to have static IPs in case on some machines some subnetworks are already in use. So with default docker-compose will find one for itself.

```
$ ../bin/integration.py info

name                ip                  running
============================================================
sdnr                172.20.0.3          running
sdncweb             172.20.0.9          running
transportpce        172.20.0.7          running
roadma              172.20.0.8          running
roadmb              172.20.0.10         running
roadmc              172.20.0.6          running
xpdra               172.20.0.4          running
xpdrc               172.20.0.5          running
```

If you like to have the ODLUX Gui started in you browser you can also directly execute ```../bin/integration.py web```. In this example it will call ```http://172.20.0.9:8080``` in your browser. Username and password are the configured ones. The same as for the restconf interface.




