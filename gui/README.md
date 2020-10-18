[![pipeline status](https://gitlab.com/Orange-OpenSource/lfn/odl/tpce_gui/badges/master/pipeline.svg)](https://gitlab.com/Orange-OpenSource/lfn/odl/tpce_gui/-/commits/master)
[![coverage report](https://gitlab.com/Orange-OpenSource/lfn/odl/tpce_gui/badges/master/coverage.svg)](https://gitlab.com/Orange-OpenSource/lfn/odl/tpce_gui/-/commits/master)


Transport PCE_GUI Project
----------------------------


**TransportPCE_GUI** is a part from **TransportPCE** that running on top of the **OpenDaylight** controller.

 Its primary function is to display a GUI that represent an optical transport infrastructure.

Full documentation and information about TransportPCE is available on our website at https://docs.opendaylight.org/projects/transportpce/en/stable-fluorine/developer-guide.html#transportpce-dev-guide

**Steps to run TPCE_GUI in Development**: 

1- First make sure you have the TransportPCE project up and runnig, and set the server url in application.properties files in
      **network-url-path**, **service-path-list** and **service-url-path** properties.
      
2- Run `mvn clean install`, then run the main project class `OnapTpceManagerApplication.java`

3- Run angular frontend project in `{project-path}/src/main/frontend/` with `npm start`

**Steps to run TPCE_GUI in Production**:

1- Generate Jar file with `mvn clean install`.

2- Deploy the jar and run it with `java -jar {jar-name}` for example
