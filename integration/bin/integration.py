#!/usr/bin/python3

import sys
import os
import os.path
import shlex
import subprocess
import datetime
from lib.config import IntegrationConfig
from lib.docker import Docker, DockerContainer
from lib.odlclient import OdlClient
from lib.trpceodlclient import TrpceOdlClient
from tests.mountingtest import MountingTest
from tests.end2endtest import End2EndTest
from lib.siminfo import SimulatorInfo

SIMS = ["roadma", "roadmb", "roadmc", "xpdra", "xpdrc"]
NODEID_LUT = dict(roadma="ROADM-A1",
    roadmb="ROADM-B1",
    roadmc="ROADM-C1",
    xpdra="XPDR-A1",
    xpdrc="XPDR-C1")

class Integration:

    def __init__(self, prefix, envFile=None):
        self.prefix = prefix
        self.config = IntegrationConfig(envFile)
        self.dockerExec = Docker()
        sdnrInfos = self.dockerExec.inspect(self.getContainerName("sdnr"))
        self.odlSdnrClient = OdlClient("http://"+sdnrInfos.getIpAddress()+":8181",
                                       self.config.getEnv("SDNR_USERNAME"), self.config.getEnv("SDNR_PASSWORD"))
        trpceInfos = self.dockerExec.inspect(
            self.getContainerName("transportpce"))
        self.odlTrpceClient = TrpceOdlClient("http://"+trpceInfos.getIpAddress()+":8181",
                                        "admin", "admin")

    
    def getContainerName(self, name, suffix="_1"):
        return self.prefix+name+suffix

    def getMountPointName(self, name, suffix="_1"):
        #return self.getContainerName(name, suffix).replace("_", "").replace(" ", "").replace("-", "")
        return NODEID_LUT[name]

    def mount(self):
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            simInfo = self.dockerExec.inspect(simContainerName)
            simMountPointName = self.getMountPointName(sim)
            if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
                self.odlSdnrClient.mount(simMountPointName, simInfo.getIpAddress(), self.config.getEnv("SIMPORT"), self.config.getEnv("SIM_NETCONF_USERNAME"), self.config.getEnv("SIM_NETCONF_PASSWORD"))
            else:
                self.odlTrpceClient.mount(simMountPointName, simInfo.getIpAddress(), self.config.getEnv("SIMPORT"), self.config.getEnv("SIM_NETCONF_USERNAME"), self.config.getEnv("SIM_NETCONF_PASSWORD"))
            #    self.odlTrpceClient.mount("testroadm", "10.20.5.2", 50000, "netconf", "netconf")
                 
            #break

    def unmount(self):
        for sim in SIMS:
            if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
                self.odlSdnrClient.unmount(self.getMountPointName(sim))
            else:
                self.odlTrpceClient.unmount(self.getMountPointName(sim))
            #self.odlTrpceClient.unmount("testroadm")

    def printInfo(self, name, info):

        print(name.ljust(20)+info.getIpAddress().ljust(20) +
              info.getContainerStatus())

    def info(self):
        # print header
        print("name".ljust(20)+"ip".ljust(20)+"running")
        print("".ljust(60, "="))
        # print controller infos
        self.printInfo("sdnr", self.dockerExec.inspect(
            self.getContainerName("sdnr")))
        self.printInfo("sdncweb", self.dockerExec.inspect(
            self.getContainerName("sdncweb")))
        self.printInfo("transportpce", self.dockerExec.inspect(
            self.getContainerName("transportpce")))

        # print sim infos
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            self.printInfo(sim, self.dockerExec.inspect(simContainerName))

    def status(self):
        for sim in SIMS:
            status = self.odlSdnrClient.neStatus(self.getMountPointName(sim))
            print(sim.ljust(20)+status)

    def collectSimInfos(self):
        sims = []
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            simInfo = self.dockerExec.inspect(simContainerName)
            simMountPointName = self.getMountPointName(sim)
            sims.append(SimulatorInfo(simMountPointName, simInfo.getIpAddress(), 
            self.config.getEnv("SIMPORT"),
            self.config.getEnv("SIM_NETCONF_USERNAME"), 
            self.config.getEnv("SIM_NETCONF_PASSWORD")))
        return sims

    def getTransportPCEContainer(self):
        return DockerContainer(self.getContainerName("transportpce"))
    def getSdnrContainer(self):
        return DockerContainer(self.getContainerName("sdnr"))

    def openBrowser(self, url):
        subprocess.Popen("/usr/bin/xdg-open "+url+" >/dev/null 2>&1 &", shell=True)

    def openSdncWebBrowser(self):
        infos = self.dockerExec.inspect(self.getContainerName("sdncweb"))
        self.openBrowser("http://"+infos.getIpAddress()+":8080")

    def openApidocsBrowser(self, container):
        if container == "sdnc":
            infos = self.dockerExec.inspect(self.getContainerName("sdnr"))
            self.openBrowser("http://"+infos.getIpAddress()+":8181/apidoc/explorer/index.html")
        elif container == "trpce":
            infos = self.dockerExec.inspect(self.getContainerName("transportpce"))
            self.openBrowser("http://"+infos.getIpAddress()+":8181/apidoc/explorer/index.html")

    def setLogs(self):
        tc = self.getTransportPCEContainer()
        tc.exec("/opt/opendaylight/bin/client 'log:set DEBUG org.opendaylight.transportpce'")
        tc.exec("/opt/opendaylight/bin/client 'log:set TRACE org.onap.ccsdk.features.sdnr.wt'")
        #tc.exec("/opt/opendaylight/bin/client 'log:set TRACE org.opendaylight.netconf'")
        
        sc = self.getSdnrContainer()
        sc.exec("/opt/opendaylight/bin/client 'log:set DEBUG org.onap.ccsdk.features.sdnr.wt'")
        
    def showTrpceLogs(self):
        tc = self.getTransportPCEContainer()
        grepFilter = "-ei org.opendaylight.transportpce -ei SdnrWebsocket -ei"
        tc.exec("/tail -f /opt/opendaylight/data/log/karaf.log | grep "+ grepFilter)

    def showCapabilities(self, device):
        if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
            response = self.odlTrpceClient.neInfos(self.getMountPointName(device))
        else:
            response = self.odlSdnrClient.neInfos(self.getMountPointName(device))
        
        if response.isSucceeded():
            caps=response.data["network-topology:node"][0]["netconf-node-topology:available-capabilities"]["available-capability"]
            print(str(caps))
            for cap in caps:
                print(str(cap["capability"]))
        else:
            print("code: "+str(response.code))
            print(response.content)

    def stopSdncBundle(self, bundleId):
        tc = self.getSdnrContainer()
        tc.exec("/opt/opendaylight/bin/client 'bundle:stop "+bundleId+"'")

    def getLogs(self, container=None):
        if not os.path.exists('logs'):
            os.makedirs('logs')
        c = Docker()
        src = "/opt/opendaylight/data/log/karaf.log"
        prefix = datetime.datetime.now(datetime.timezone.utc).strftime("%Y%m%dT%H%M%SZ")
        if container == "sdnc" or container is None:
            c.copy(self.getContainerName("sdnr"),src,"logs/"+prefix+"_sdnr.log")
        if container == "trpce" or container is None:
            c.copy(self.getContainerName("transportpce"),src,"logs/"+prefix+"_trpce.log")
        
    def executeTest(self, test):
        if test == "1":
            test = MountingTest(self.odlSdnrClient, self.odlTrpceClient,
                                self.getTransportPCEContainer(), self.collectSimInfos())
            test.test1()
        elif test == "2":
            test = MountingTest(self.odlSdnrClient, self.odlTrpceClient,
                                self.getTransportPCEContainer(), self.collectSimInfos())
            test.test2()
        elif test == "3":
            test = End2EndTest(self.odlSdnrClient, self.odlTrpceClient,
                self.getTransportPCEContainer(),self.collectSimInfos(),self.config)
            test.test()


def print_help():
    print("TransportPCE into ONAP integration script")
    print("=========================================")
    print("usage:")
    print("  integration.py COMMAND [ARGS]")
    print("COMMANDS:")
    print("  info                  show docker container information")
    print("  status                show simulator states")
    print("  mount                 mount simulators")
    print("  unmount               unmount simulators")
    print("  setlogs               set more logs for sdnr and trpce")
    print("  test [test-number]    start integration test")
    print("  caps [device]         show capabilities of device")
    print("  bstop [bundleId]      stop bundle in SDN-R container")
    print("  web                   open ODLUX Gui in browser")
    print("  apidocs [trpce|sdnr]  open apidocs in Browser ")
   


if __name__ == "__main__":
    if len(sys.argv) < 1:
        print_help()
        exit(1)

    execDirAbs = os.getcwd()
    cmd = sys.argv[1]

    # always autodetect prefix by exec folder
    tmp = execDirAbs.split("/")
    prefix = tmp[len(tmp)-1]
    prefix = prefix.replace("-", "").replace(" ", "_")+"_"
    # newest docker version doesnt replace '-' in folders to prefix
    d = Docker()
    if not d.exists(prefix+"sdnr_1"):
        prefix = prefix.replace(" ", "_")+"_"

    
    envFilename = execDirAbs+"/.env"
    if not os.path.isfile(envFilename):
        envFilename = None
    integration = Integration(prefix, envFilename)

    if cmd == "info":
        integration.info()
    elif cmd == "status":
        integration.status()
    elif cmd == "mount":
        integration.mount()
    elif cmd == "unmount":
        integration.unmount()
    elif cmd == "setlogs":
        integration.setLogs()
    elif cmd == "test":
        if len(sys.argv) > 2:
            integration.executeTest(sys.argv[2])
        else:
            exit(1)
    elif cmd == "caps":
        integration.showCapabilities(sys.argv[2])
    elif cmd == "bstop":
        integration.stopSdncBundle(sys.argv[2])
    elif cmd == "getlogs":
        if len(sys.argv) >2:
            integration.getLogs(sys.argv[2])
        else:
            integration.getLogs()
    elif cmd == "web":
        integration.openSdncWebBrowser()
    elif cmd == "apidocs":
        if len(sys.argv) > 2:
            integration.openApidocsBrowser(sys.argv[2])
        else:
            exit(1)
        
    else:
        exit(1)
