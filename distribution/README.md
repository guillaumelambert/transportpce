# TransportPCE distributions

build and run docker images with transportPCE

## standalone image

standalone distribution

prerequisites:
  * build artifacts including installer

### build
```
mvn clean install
```
This creates a docker image 'odl/transportpce' with the latest tag.

### run
```
docker run -d odl/transportpce
```

## ONAP

run transportPCE as a microservice for ONAP SDNC

prerequisites: 
  * build the standalone dist

### containers

  * SDNC in configuration to run SDN-R functionality
  * SDNC-web to show ODLUX UI
  * SDNRDB as database
  * transportPCE


### run

```
docker-compose up -d
```

