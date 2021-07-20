import time;

class BaseTest:

    def __init__(self, sdncClients, trpceClient, sims, config):
        self.sdncClients = sdncClients
        self.primarySdncClient = None
        idx=0
        for c in self.sdncClients:
            if c.isPrimary():
                self.primarySdncClient=c
                self.sdncClients.pop(idx)
            idx+=1
        self.trpceClient = trpceClient
        self.sims = sims
        self.config = config

    def getSdncClient(self, idx, primary=False):
        if (primary and self.primarySdncClient!=None) or len(self.sdncClients) == 0:
            return self.primarySdncClient
        return self.sdncClients[idx % len(self.sdncClients)]

    def waitForReadyState(self, timeout=60):
        while timeout>0:
            if self.config.isRemoteEnabled():
                ready = self.trpceClient.isReady()
                if ready:
                    if self.primarySdncClient!=None:
                        ready= self.primarySdncClient.isReady()
                    for c in self.sdncClients:
                        ready &= c.isReady()
            else:
                ready = self.trpceClient.isReady()
            if ready:
                return True
            timeout-=1
            time.sleep(1)
        return False

    def waitForConnectedState(self, timeout=60):
        while timeout>0:
            allConnected = True
            idx=0
            for sim in self.sims:
                connected = False
                if self.config.isRemoteEnabled():
                    client=self.getSdncClient(idx,self.primarySdncClient!=None)
                    connected = client.neStatus(sim.name)=="connected"
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


        

    