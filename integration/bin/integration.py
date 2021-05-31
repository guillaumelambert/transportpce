#!/usr/bin/python3

import sys
import os
import os.path
import shlex
import subprocess
import datetime
import time
from lib.config import IntegrationConfig
from lib.docker import Docker, DockerContainer
from lib.odlclient import OdlClient
from lib.trpceodlclient import TrpceOdlClient
from tests.mountingtest import MountingTest
from tests.end2endtest import End2EndTest
from lib.siminfo import SimulatorInfo

SIMS = ["roadma", "roadmb", "roadmc", "xpdra", "xpdrc"]
SIMS_DEMO2 = ["roadma", "roadmb", "roadmc", "xpdra", "xpdrc"]

NODEID_LUT = dict(roadma="ROADM-A1",roadmb="ROADM-B1",
    roadmc="ROADM-C1",
    xpdra="XPDR-A1",
    xpdrc="XPDR-C1")

class Integration:

    def __init__(self, prefix, envFile=None, sdnrHost=None, trpceHost=None):
        self.prefix = prefix
        self.config = IntegrationConfig(envFile)
        self.dockerExec = Docker()
        self.odlSdnrClient = None
        if self.config.isRemoteEnabled():
            if sdnrHost is None:
                sdnrInfos = self.dockerExec.inspect(self.getContainerName("sdnr"))
                sdnrHost = "http://"+sdnrInfos.getIpAddress()+":8181"
            self.odlSdnrClient = OdlClient(sdnrHost, self.config.getEnv("SDNR_USERNAME"), self.config.getEnv("SDNR_PASSWORD"))
        if trpceHost is None:
            trpceInfos = self.dockerExec.inspect(self.getContainerName("transportpce"))
            trpceHost = "http://"+trpceInfos.getIpAddress()+":8181"
        self.odlTrpceClient = TrpceOdlClient(trpceHost, "admin", "admin")

    
    def getContainerName(self, name, suffix="_1"):
        return self.prefix+name+suffix

    def getMountPointName(self, name, suffix="_1"):
        #return self.getContainerName(name, suffix).replace("_", "").replace(" ", "").replace("-", "")
        return NODEID_LUT[name]

    def mount(self,args):
        mode = args.pop(0) if len(args)>0 else 'default'
        if mode == "demo":
           infos = self.collectSimInfos(mode)
           for info in infos:
                if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
                    self.odlSdnrClient.mount(info.name, info.ip, info.port, info.username, info.password)
                else:
                    self.odlTrpceClient.mount(info.name, info.ip, info.port, info.username, info.password)
        
        elif mode == 'trpce' and self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
            info = self.getTransportPCEConsoleInfo()
            self.odlSdnrClient.mount(info.name, info.ip, info.port, info.username, info.password)

        else:
            for sim in SIMS:
                simContainerName = self.getContainerName(sim)
                simInfo = self.dockerExec.inspect(simContainerName)
                simMountPointName = self.getMountPointName(sim)
                if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
                    self.odlSdnrClient.mount(simMountPointName, simInfo.getIpAddress(), self.config.getEnv("SIMPORT"), self.config.getEnv("SIM_NETCONF_USERNAME"), self.config.getEnv("SIM_NETCONF_PASSWORD"))
                else:
                    self.odlTrpceClient.mount(simMountPointName, simInfo.getIpAddress(), self.config.getEnv("SIMPORT"), self.config.getEnv("SIM_NETCONF_USERNAME"), self.config.getEnv("SIM_NETCONF_PASSWORD"))
    
    def unmount(self,args):
        mode = args.pop(0) if len(args)>0 else 'default'
        if mode == "demo":
           infos = self.collectSimInfos(mode)
           for info in infos:
                if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
                    self.odlSdnrClient.unmount(info.name)
                else:
                    self.odlTrpceClient.unmount(info.name)
        elif mode == 'trpce' and self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
            info = self.getTransportPCEConsoleInfo()
            self.odlSdnrClient.unmount(info.name)

        else:
            for sim in SIMS:
                simContainerName = self.getContainerName(sim)
                simInfo = self.dockerExec.inspect(simContainerName)
                simMountPointName = self.getMountPointName(sim)
                if self.config.getEnv("REMOTE_ODL_ENABLED") == "true":
                    self.odlSdnrClient.unmount(simMountPointName)
                else:
                    self.odlTrpceClient.unmount(simMountPointName)
    

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
        self.printInfo("transportpce-gui", self.dockerExec.inspect(
            self.getContainerName("transportpce-gui")))

        # print sim infos
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            self.printInfo(sim, self.dockerExec.inspect(simContainerName))

    def status(self):
        for sim in SIMS:
            if self.config.isRemoteEnabled():
                status = self.odlSdnrClient.neStatus(self.getMountPointName(sim))
            else:
                status = self.odlTrpceClient.neStatus(self.getMountPointName(sim))
                
            print(sim.ljust(20)+status)

    def collectSimInfos(self,mode='default'):
        sims = []
        if mode == 'default':
            for sim in SIMS:
                simContainerName = self.getContainerName(sim)
                simInfo = self.dockerExec.inspect(simContainerName)
                simMountPointName = self.getMountPointName(sim)
                sims.append(SimulatorInfo(simMountPointName, simInfo.getIpAddress(), 
                self.config.getEnv("SIMPORT"),
                self.config.getEnv("SIM_NETCONF_USERNAME"), 
                self.config.getEnv("SIM_NETCONF_PASSWORD")))
        elif mode == 'demo':
            DEMO_MEDIATOR_IP_ADDR = "10.20.6.49"
            DEMO_NETCONF_USERNAME = "admin"
            DEMO_NETCONF_PASSWORD = "admin"
            sims.append(SimulatorInfo("ROADM-A", DEMO_MEDIATOR_IP_ADDR, 
                17931, DEMO_NETCONF_USERNAME, DEMO_NETCONF_PASSWORD))
            sims.append(SimulatorInfo("ROADM-B", DEMO_MEDIATOR_IP_ADDR, 
                17932, DEMO_NETCONF_USERNAME, DEMO_NETCONF_PASSWORD))
            sims.append(SimulatorInfo("ROADM-C", DEMO_MEDIATOR_IP_ADDR, 
                17933, DEMO_NETCONF_USERNAME, DEMO_NETCONF_PASSWORD))
        elif mode == 'demo2':
            DEMO2_MEDIATOR_IP_ADDR = "10.20.6.32"
            DEMO2_NETCONF_USERNAME = "netconf"
            DEMO2_NETCONF_PASSWORD = "netconf"
            sims.append(SimulatorInfo("ROADM-A1", DEMO2_MEDIATOR_IP_ADDR, 
                50000, DEMO2_NETCONF_USERNAME, DEMO2_NETCONF_PASSWORD))
            sims.append(SimulatorInfo("ROADM-B1", DEMO2_MEDIATOR_IP_ADDR, 
                50001, DEMO2_NETCONF_USERNAME, DEMO2_NETCONF_PASSWORD))
            sims.append(SimulatorInfo("ROADM-C1", DEMO2_MEDIATOR_IP_ADDR, 
                50002, DEMO2_NETCONF_USERNAME, DEMO2_NETCONF_PASSWORD))
            sims.append(SimulatorInfo("XPDR-A1", DEMO2_MEDIATOR_IP_ADDR, 
                50003, DEMO2_NETCONF_USERNAME, DEMO2_NETCONF_PASSWORD))
            sims.append(SimulatorInfo("XPDR-C1", DEMO2_MEDIATOR_IP_ADDR, 
                50004, DEMO2_NETCONF_USERNAME, DEMO2_NETCONF_PASSWORD))
        return sims

    def getTransportPCEContainer(self):
        return DockerContainer(self.getContainerName("transportpce"))
 
    def getSdnrContainer(self):
        return DockerContainer(self.getContainerName("sdnr"))

    def getTransportPCEConsoleInfo(self):
        c = self.dockerExec.inspect(self.getContainerName("transportpce"))

        return SimulatorInfo("transportPCE", c.getIpAddress(),2830, "admin", "admin")


    def openBrowser(self, url):
        subprocess.Popen("/usr/bin/xdg-open "+url+" >/dev/null 2>&1 &", shell=True)

    def openSdncWebBrowser(self):
        infos = self.dockerExec.inspect(self.getContainerName("sdncweb"))
        self.openBrowser("http://"+infos.getIpAddress()+":8080")
    def openTrpceGuiWebBrowser(self):
        infos = self.dockerExec.inspect(self.getContainerName("transportpce-gui"))
        self.openBrowser("http://"+infos.getIpAddress()+":8082")

    def openApidocsBrowser(self, args):
        container = args.pop(0)
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
        if self.config.isRemoteEnabled():
            sc = self.getSdnrContainer()
            sc.exec("/opt/opendaylight/bin/client 'log:set DEBUG org.onap.ccsdk.features.sdnr.wt'")
            sc.exec("/opt/opendaylight/bin/client 'log:set TRACE org.opendaylight.netconf'")    
        else:
            tc.exec("/opt/opendaylight/bin/client 'log:set TRACE org.opendaylight.netconf'")    
        
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

    def getLogs(self, args=[]):
        container = None
        if len(args)>0:
            container=args.pop(0)
        if not os.path.exists('logs'):
            os.makedirs('logs')
        c = Docker()
        src = "/opt/opendaylight/data/log/karaf.log"
        prefix = datetime.datetime.now(datetime.timezone.utc).strftime("%Y%m%dT%H%M%SZ")
        if container == "sdnc" or container is None:
            c.copy(self.getContainerName("sdnr"),src,"logs/"+prefix+"_sdnr.log")
        if container == "trpce" or container is None:
            c.copy(self.getContainerName("transportpce"),src,"logs/"+prefix+"_trpce.log")

    def waitForReadyState(self, args=[]):
        timeout=60
        print("waiting for ready state (max "+str(timeout) + "sec)...",end="")
        while timeout>0:
            if self.config.isRemoteEnabled():
                ready = self.odlTrpceClient.isReady() and self.odlSdnrClient.isReady()
            else:
                ready  = self.odlTrpceClient.isReady()
            
            if ready:
                print("ready!")
                return True
            timeout-=1
            print(".",end="")
            time.sleep(1)
        return False
        
    def executeTest(self, args):
        test = args.pop(0)
        mode = args.pop(0) if len(args)>0 else 'default'
        if test == "1":
            test = MountingTest(self.odlSdnrClient, self.odlTrpceClient,
                                self.getTransportPCEContainer(), self.collectSimInfos(mode))
            test.test1()
        elif test == "2":
            test = MountingTest(self.odlSdnrClient, self.odlTrpceClient,
                                self.getTransportPCEContainer(), self.collectSimInfos(mode))
            test.test2()
        elif test == "end2end":
            test = End2EndTest(self.odlSdnrClient, self.odlTrpceClient,
                self.getTransportPCEContainer(),self.collectSimInfos(mode),self.config)
            test.test(args)
        elif test == "demo":
            test = End2EndTest(self.odlSdnrClient, self.odlTrpceClient,
                self.getTransportPCEContainer(),self.collectSimInfos('demo'),self.config)
            test.test(args)
        else:
            print('unknown command: '+test)


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
    sys.argv.pop(0)

    sdnrHost = None
    trpceHost = None
    if len(sys.argv)>0 and sys.argv[0]=="--sdnr":
        sys.argv.pop(0)
        sdnrHost = sys.argv.pop(0)
        print("overwriting sdnr host: "+sdnrHost)
    if len(sys.argv)>0 and sys.argv[0]=="--trpce":
        sys.argv.pop(0)
        trpceHost = sys.argv.pop(0)
        print("overwriting trpce host: "+trpceHost)

    cmd = sys.argv.pop(0)

    # always autodetect prefix by exec folder
    tmp = execDirAbs.split("/")
    prefix = tmp[len(tmp)-1]
    prefix = prefix.replace("-", "").replace(" ", "_")+"_"
    # newest docker version doesnt replace '-' in folders to prefix
    d = Docker()
    if not d.exists(prefix+"sdnr_1"):
        prefix = tmp[len(tmp)-1]
        prefix = prefix.replace(" ", "_")+"_"

    
    envFilename = execDirAbs+"/.env"
    if not os.path.isfile(envFilename):
        envFilename = None
    integration = Integration(prefix, envFilename, sdnrHost, trpceHost)
    if cmd == "info":
        integration.info()
    elif cmd == "status":
        integration.status()
    elif cmd == "mount":
        integration.mount(sys.argv)
    elif cmd == "unmount":
        integration.unmount(sys.argv)
    elif cmd == "isready":
        integration.waitForReadyState(sys.argv)
    elif cmd == "setlogs":
        integration.setLogs()
    elif cmd == "test":
        if len(sys.argv) > 0:
            integration.executeTest(sys.argv)
        else:
            exit(1)
    elif cmd == "caps":
        integration.showCapabilities(sys.argv)
    elif cmd == "bstop":
        integration.stopSdncBundle(sys.argv)
    elif cmd == "getlogs":
        integration.getLogs(sys.argv)
    elif cmd == "web":
        integration.openSdncWebBrowser()
    elif cmd == "webtrpce":
        integration.openTrpceGuiWebBrowser()
    elif cmd == "apidocs":
        if len(sys.argv) > 0:
            integration.openApidocsBrowser(sys.argv)
        else:
            exit(1)
        
    else:
        exit(1)
