import time;

class BaseTest:

    def __init__(self, sdncClient, trpceClient, sims, config):
        self.sdncClient = sdncClient
        self.trpceClient = trpceClient
        self.sims = sims
        self.config = config

    def waitForReadyState(self, timeout=60):
        while timeout>0:
            ready = self.trpceClient.isReady() and self.sdncClient.isReady()
            if ready:
                return True
            timeout-=1
            time.sleep(1)
        return False

    def waitForConnectedState(self, timeout=60):
        while timeout>0:
            allConnected = True
            for sim in self.sims:
                connected = False
                if self.config.isRemoteEnabled():
                    connected = self.sdncClient.neStatus(sim.name)=="connected"
                else:
                    connected = self.trpceClient.neStatus(sim.name)=="connected"
                allConnected = allConnected and connected
                if not allConnected:
                    break
            
            if allConnected:
                return True
            timeout-=1
            time.sleep(1)

        return False


        

    