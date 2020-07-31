#!/usr/bin/python3

import sys
import os
import os.path
import shlex
import subprocess
from lib.docker import Docker, DockerContainer
from lib.odlclient import OdlClient
from tests.mountingtest import MountingTest
from lib.siminfo import SimulatorInfo

SIMS = ["roadma", "roadmb", "roadmc", "xpdra", "xpdrc"]


class Integration:

    def __init__(self, prefix, envFile=None):
        self.prefix = prefix
        self.envs = {}
        if envFile != None:
            self.source(envFile)
        self.dockerExec = Docker()
        sdnrInfos = self.dockerExec.inspect(self.getContainerName("sdnr"))
        self.odlSdnrClient = OdlClient("http://"+sdnrInfos.getIpAddress()+":8181",
                                       self.getEnv("SDNR_USERNAME"), self.getEnv("SDNR_PASSWORD"))
        trpceInfos = self.dockerExec.inspect(
            self.getContainerName("transportpce"))
        self.odlTrpceClient = OdlClient("http://"+trpceInfos.getIpAddress()+":8181",
                                        "admin", "admin")

    def source(self, envFilename):
        print("sourcing "+envFilename)
        with open(envFilename) as f:
            lines = f.readlines()
            for line in lines:
                if line.startswith("#"):
                    continue
                (key, _, value) = line.partition("=")
                self.envs[key] = value.strip()

    def getEnv(self, env):
        e = ""
        if env in self.envs:
            return self.envs[env]
        try:
            e = os.environ[env]
        except KeyError:
            print("unable to find env for "+env)
            #print("these are available")
            # print(os.environ)
        return e

    def getContainerName(self, name, suffix="_1"):
        return self.prefix+name+suffix

    def getMountPointName(self, name, suffix="_1"):
        return self.getContainerName(name, suffix).replace("_", "").replace(" ", "").replace("-", "")

    def mount(self):
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            simInfo = self.dockerExec.inspect(simContainerName)
            simMountPointName = self.getMountPointName(sim)
            if self.getEnv("REMOTE_ODL_ENABLED") == "true":
                self.odlSdnrClient.mount(simMountPointName, simInfo.getIpAddress(), self.getEnv("SIMPORT"), self.getEnv("SIM_NETCONF_USERNAME"), self.getEnv("SIM_NETCONF_PASSWORD"))
            else:
                self.odlTrpceClient.mount(simMountPointName, simInfo.getIpAddress(), self.getEnv("SIMPORT"), self.getEnv("SIM_NETCONF_USERNAME"), self.getEnv("SIM_NETCONF_PASSWORD"))
            #    self.odlTrpceClient.mount("testroadm", "10.20.5.2", 50000, "netconf", "netconf")
                 
            break

    def unmount(self):
        for sim in SIMS:
            if self.getEnv("REMOTE_ODL_ENABLED") == "true":
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
            sims.append(SimulatorInfo(simMountPointName, simInfo.getIpAddress(
            ), self.getEnv("SIMPORT"), "asd", "asd"))
        return sims

    def getTransportPCEContainer(self):
        return DockerContainer(self.getContainerName("transportpce"))
    def getSdnrContainer(self):
        return DockerContainer(self.getContainerName("sdnr"))

    def openSdncWebBrowser(self):
        infos = self.dockerExec.inspect(self.getContainerName("sdncweb"))
        subprocess.Popen('/usr/bin/xdg-open http://' +
                         infos.getIpAddress()+":8080 >/dev/null 2>&1 &", shell=True)

    def setLogs(self):
        tc = self.getTransportPCEContainer()
        tc.exec("/opt/opendaylight/bin/client 'log:set DEBUG org.opendaylight.transportpce'")
        tc.exec("/opt/opendaylight/bin/client 'log:set TRACE org.onap.ccsdk.features.sdnr.wt'")
        #tc.exec("/opt/opendaylight/bin/client 'log:set TRACE org.opendaylight.netconf'")
        
        sc = self.getSdnrContainer()
        sc.exec("/opt/opendaylight/bin/client 'log:set DEBUG org.onap.ccsdk.features.sdnr.wt'")
        
    def showTrpceLogs(self):
        tc = self.getTransportPCEContainer()
        grepFilter = "-ei org\.opendaylight\.transportpce -ei SdnrWebsocket -ei"
        tc.exec("/tail -f /opt/opendaylight/data/log/karaf.log | grep "+ grepFilter)

    def executeTest(self, test):
        if test == "1":
            test = MountingTest(self.odlSdnrClient, self.odlTrpceClient,
                                self.getTransportPCEContainer(), self.collectSimInfos())
            test.test1()
        elif test == "2":
            test = MountingTest(self.odlSdnrClient, self.odlTrpceClient,
                                self.getTransportPCEContainer(), self.collectSimInfos())
            test.test2()


def print_help():
    print("help")


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
    elif cmd == "web":
        integration.openSdncWebBrowser()
    else:
        exit(1)
