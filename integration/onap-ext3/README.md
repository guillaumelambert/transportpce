# Integration: ONAP SDNC with transportPCE and Honeynode Simulators

## Prerequisites

  * NTSimulator for OpenROADM with 2.2.1 Model

## Configure

just config the params in the ```.env``` file.

## How to start

```
docker-compose up -d
```

## Tests

### Mount remotely

```
../bin/integration.py mount
```

### End2End test

for remote enabled=true
```
# set log levels
../bin/integration.py setlogs
# stops openroadm devicemanager provider (due a bug)
../bin/integration.py bstop 207
# start test
../bin/integration.py test 3
```

if it fails and you need logs
```
../bin/integration.py getlogs
```
