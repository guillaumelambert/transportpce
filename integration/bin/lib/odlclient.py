import urllib3
import json
import base64

class OdlResponse:

    def __init__(self, r):
        self.code = r.status
        self.content = r.data.decode('utf-8')

    def isSucceeded(self):
        return self.code>=200 and self.code<300
    
    def sourceNotFound(self):
        return self.code==404

class OdlClient:

    def __init__(self, baseUrl, username, password):

        self.baseUrl = baseUrl
        self.username = username
        self.password = password
        self.defaultJsonHeaders = dict()
        self.defaultJsonHeaders['Content-Type']='application/json'
        self.defaultJsonHeaders['Accept']='application/json'
        self.defaultJsonHeaders['Authorization']="Basic "+str(base64.b64encode((username+":"+password).encode('utf-8')),'utf-8')

    def requestRest(self, uri, method, headers=dict(), data=None):
        http = urllib3.PoolManager()
        r = None
        if data == None:
            r = http.request(method, self.baseUrl+uri, headers=headers)
        else:
            encoded_data = data.encode('utf-8')
            r = http.request(method, self.baseUrl+uri,
                             body=encoded_data, headers=headers)
        return OdlResponse(r)

    def mount(self, name, ip, port, username, password):
        data = dict()
        node = dict()
        data["node"] = node
        node["node-id"] = name
        node["netconf-node-topology:port"] = port
        node["netconf-node-topology:host"] = ip
        node["netconf-node-topology:username"] = username
        node["netconf-node-topology:password"] = password
        node["netconf-node-topology:tcp-only"] = False
        node["netconf-node-topology:reconnect-on-changed-schema"] = False
        node["netconf-node-topology:connection-timeout-millis"] = 20000
        node["netconf-node-topology:max-connection-attempts"] = 100
        node["netconf-node-topology:between-attempts-timeout-millis"] = 2000
        node["netconf-node-topology:sleep-factor"] = 1.5
        node["netconf-node-topology:keepalive-delay"] = 120
        payload = json.dumps(data)
        response = self.requestRest('/rests/data/network-topology:network-topology/topology=topology-netconf',
                                    'POST', self.defaultJsonHeaders, payload)
        print(name + " mounted "+str(response.code))

    def unmount(self, name):
        response = self.requestRest('/rests/data/network-topology:network-topology/topology=topology-netconf/node='+name,
                                    'DELETE', self.defaultJsonHeaders)
        print(name + " unmounted "+str(response.code))

    def neStatus(self, name):
        response = self.requestRest('/rests/data/network-topology:network-topology/topology=topology-netconf/node='+name,
                                    'GET', self.defaultJsonHeaders)
        if response.isSucceeded():
            connectionStatus="unknown"
            try:
                o=json.loads(response.content)
                connectionStatus=o["network-topology:node"][0]["netconf-node-topology:connection-status"]
            except:
                print()
            return connectionStatus
        elif response.sourceNotFound():
            return "unmounted"
        else:
            return "unknown"
