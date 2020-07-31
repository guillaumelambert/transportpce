# Integration: ONAP SDNC with transportPCE and Honeynode Simulators

## Prerequisites

 * build honeynode docker image

```
  cd tests/Xtesting/DockerSims/
  ./build_sims.sh
```   

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

