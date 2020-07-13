import sys
import subprocess
import json

class InspectInfo:

    def __init__(self, jsonString):
        try:
            self.state="unknown"
            self.ip = "unknown"
            o=json.loads(jsonString)
            self.state=o[0]["State"]["Status"]
            networks=o[0]["NetworkSettings"]["Networks"]
            self.ip = networks[list(networks)[0]]["IPAddress"]

        except:
            print("WARN: unable to parse docker inspect info")
    def getIpAddress(self):
        return self.ip
    def getContainerStatus(self):
        return self.state

class Docker:


    def __init__(self):
        self.bin="/usr/bin/docker"
        pass

    def exec(self, params):
        output=subprocess.Popen(self.bin+" "+params, shell=True, stdout=subprocess.PIPE).stdout.read()
        return output

    def inspect(self,name):
        return InspectInfo(self.exec("inspect "+name))

    def status(self,name):
        pass

