import json
from .odlclient import OdlClient

URI_CONFIG_ORDM_TOPO = "/restconf/config/ietf-network:networks/network/openroadm-topology/"


class TrpceOdlClient(OdlClient):

    def __init__(self, baseUrl, username, password):
        OdlClient.__init__(self, baseUrl, username, password)

    def linkRoadmToRoadm(self, roadmANodeId, roadmADeg, roadmATermPoint, roadmZNodeId, roadmZDeg, roadmZTermPoint):
        data = {
            "networkutils:input": {
                "networkutils:rdm-a-node": roadmANodeId,
                "networkutils:deg-a-num": roadmADeg,
                "networkutils:termination-point-a": roadmATermPoint,
                "networkutils:rdm-z-node": roadmZNodeId,
                "networkutils:deg-z-num": roadmZDeg,
                "networkutils:termination-point-z": roadmZTermPoint
            }
        }
        payload = json.dumps(data)
        response = self.requestRest('/rests/operations/transportpce-networkutils:init-roadm-nodes',
                                    'POST', self.defaultJsonHeaders, payload)
        print(roadmANodeId + " to "+ roadmZNodeId+" link "+str(response.code))
        return response
    
    def linkXpdrToRoadm(self, xpdrNode, xpdrNum, xpdrNetworkPortNumber, roadmNodeId, srgNumber, logicalConnectionPoint):
        data ={
            "networkutils:input": {
                "networkutils:links-input": {
                "networkutils:xpdr-node": xpdrNode,
                "networkutils:xpdr-num": str(xpdrNum),
                "networkutils:network-num": str(xpdrNetworkPortNumber),
                "networkutils:rdm-node": roadmNodeId,
                "networkutils:srg-num": str(srgNumber),
                "networkutils:termination-point-num": logicalConnectionPoint
                }
            }
        }
        payload = json.dumps(data)
        response = self.requestRest('/rests/operations/transportpce-networkutils:init-xpdr-rdm-links',
                                    'POST', self.defaultJsonHeaders, payload)
        print(xpdrNode + " to "+ roadmNodeId+" link "+str(response.code))
        return response
    
    
    def linkRoadmTpXpdr(self, xpdrNode, xpdrNum, xpdrNetworkPortNumber, roadmNodeId, srgNumber, logicalConnectionPoint):
        data ={
            "networkutils:input": {
                "networkutils:links-input": {
                "networkutils:xpdr-node": xpdrNode,
                "networkutils:xpdr-num": str(xpdrNum),
                "networkutils:network-num": str(xpdrNetworkPortNumber),
                "networkutils:rdm-node": roadmNodeId,
                "networkutils:srg-num": str(srgNumber),
                "networkutils:termination-point-num": logicalConnectionPoint
                }
            }
        }
        payload = json.dumps(data)
        response = self.requestRest('/rests/operations/transportpce-networkutils:init-rdm-xpdr-links',
                                    'POST', self.defaultJsonHeaders, payload)
        print(xpdrNode + " to "+ roadmNodeId+" link "+str(response.code))
        return response

    def addOmsAttributes(self, link: str, attr):
        uri = URI_CONFIG_ORDM_TOPO + (
            "ietf-network-topology:link/" + link + "/org-openroadm-network-topology:OMS-attributes/span"
        )
        response = self.requestRest(uri,
            'PUT', self.defaultJsonHeaders, attr)
        return response
    
    def createTopoLink(self, linkId, srcXpdrNodeId, srcXpdrNetworkPortId, dstXpdrNodeId, dstXpdrNetworkPortId):
        data={
            "ietf-network-topology:link": [
            {
                "link-id": linkId,
                "source": {
                    "source-node": srcXpdrNodeId,
                    "source-tp": srcXpdrNetworkPortId
                },
                "org-openroadm-common-network:link-type": "OTN-LINK",
                "destination": {
                    "dest-node": dstXpdrNodeId,
                    "dest-tp": dstXpdrNetworkPortId
                }
            }]
        }
        payload = json.dumps(data)
        response = self.requestRest('/rests/data/ietf-network:networks/network/otn-topology/ietf-network-topology:link/'+linkId,
                                    'POST', self.defaultJsonHeaders, payload)
        print("topolink from "+srcXpdrNodeId + " to "+ dstXpdrNodeId+" created "+str(response.code))
        return response

    def createService(self,serviceData):
       
        response = self.requestRest('/restconf/operations/org-openroadm-service:service-create',
            'POST',self.defaultJsonHeaders,serviceData)
   
        return response

    def getService(self, serviceName):
        uri = "/restconf/operational/org-openroadm-service:service-list/services/"+serviceName
        response = self.requestRest(uri,'GET',self.defaultJsonHeaders,None)
        return response

    def getOpenroadmTopology(self, suffix):
        return self.requestRest(URI_CONFIG_ORDM_TOPO+suffix,'GET',self.defaultJsonHeaders)
        
    def deleteService(self, serviceData):
        response = self.requestRest('/restconf/operations/org-openroadm-service:service-delete',
            'POST', self.defaultJsonHeaders,serviceData)
        return response