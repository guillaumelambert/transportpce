#!/usr/bin/python3

import sys
import os
import os.path
import shlex
import subprocess
from lib.docker import Docker
from lib.odlclient import OdlClient

SIMS = ["roadma", "roadmb", "roadmc", "xpdra", "xpdrc"]
class Integration:

    def __init__(self, prefix,envFile=None):
        self.prefix=prefix
        self.envs={}
        if envFile != None:
            self.source(envFile)
        self.dockerExec = Docker()
        sdnrInfos = self.dockerExec.inspect(self.getContainerName("sdnr"))
        self.odlSdnrClient = OdlClient("http://"+sdnrInfos.getIpAddress()+":8181",
        self.getEnv("SDNR_USERNAME"),self.getEnv("SDNR_PASSWORD"))
        trpceInfos = self.dockerExec.inspect(self.getContainerName("transportpce"))
        self.odlTrpceClient =  OdlClient("http://"+trpceInfos.getIpAddress()+":8181",
        "admin","admin")

    def source(self, envFilename):
        print("sourcing "+envFilename)
        with open(envFilename) as f:
            lines = f.readlines()
            for line in lines:
                (key, _, value) = line.partition("=")
                self.envs[key] = value.strip()

    def getEnv(self, env):
        e=""
        if env in self.envs:
            return self.envs[env]
        try:
            e=os.environ[env]
        except KeyError:
            print("unable to find env for "+env)
            #print("these are available")
            #print(os.environ)
        return e
    
    def getContainerName(self,name, suffix="_1"):
        return self.prefix+name+suffix
    def getMountPointName(self, name, suffix="_1"):
        return self.getContainerName(name,suffix).replace("_","").replace(" ","").replace("-","")

    def mount(self):
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            simInfo = self.dockerExec.inspect(simContainerName)
            simMountPointName = self.getMountPointName(sim)
            self.odlSdnrClient.mount(simMountPointName,simInfo.getIpAddress(),self.getEnv("SIMPORT"),"asd","asd")

    def unmount(self):
        for sim in SIMS:
            self.odlSdnrClient.unmount(self.getMountPointName(sim))

    def printInfo(self,name, info):
        
        print(name.ljust(20)+info.getIpAddress().ljust(20)+info.getContainerStatus())
 
    def info(self):
        # print header
        print("name".ljust(20)+"ip".ljust(20)+"running")
        print("".ljust(60,"="))
        #print controller infos
        self.printInfo("sdnr",self.dockerExec.inspect(self.getContainerName("sdnr")))
        self.printInfo("sdncweb",self.dockerExec.inspect(self.getContainerName("sdncweb")))
        self.printInfo("transportpce",self.dockerExec.inspect(self.getContainerName("transportpce")))
        
        #print sim infos
        for sim in SIMS:
            simContainerName = self.getContainerName(sim)
            self.printInfo(sim,self.dockerExec.inspect(simContainerName))

    def status(self):
        for sim in SIMS:
            status=self.odlSdnrClient.neStatus(self.getMountPointName(sim))
            print(sim.ljust(20)+status)

    def openSdncWebBrowser(self):
        infos=self.dockerExec.inspect(self.getContainerName("sdncweb"))
        subprocess.Popen('/usr/bin/xdg-open http://'+infos.getIpAddress()+":8080 >/dev/null 2>&1 &", shell=True)


def print_help():
    print ("help")

if __name__ == "__main__":
    if len(sys.argv)<1:
        print_help()
        exit(1)
    
    execDirAbs=os.getcwd()
    if len(sys.argv)>2:
        prefix=sys.argv[2]
    else:
        tmp=execDirAbs.split("/")
        prefix=tmp[len(tmp)-1]
        prefix=prefix.replace("-","").replace(" ","_")+"_"
    envFilename=execDirAbs+"/.env"
    if not os.path.isfile(envFilename):
        envFilename=None
    integration = Integration(prefix,envFilename)
    cmd=sys.argv[1]
    
    if cmd=="info":
        integration.info()
    elif cmd=="status":
        integration.status()
    elif cmd=="mount":
        integration.mount()
    elif cmd=="unmount":
        integration.unmount()
    elif cmd=="web":
        integration.openSdncWebBrowser()

    