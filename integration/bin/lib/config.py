import sys
import os
import os.path

class IntegrationConfig:


    def __init__(self,envFile):
        
        self.envs = {}
        if envFile != None:
            self.source(envFile)

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

    def isRemoteEnabled(self):
        return self.getEnv("REMOTE_ODL_ENABLED") == "true"