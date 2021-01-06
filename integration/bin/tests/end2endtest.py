 def test4CreateOcService1(self):
        data = {"input": {
                "sdnc-request-header": {
                    "request-id": "e3028bae-a90f-4ddd-a83f-cf224eba0e58",
                    "rpc-action": "service-create",
                    "request-system-id": "appname",
                    "notification-url": "http://localhost:8585/NotificationServer/notify"
                },
                "service-name": "serviceoc1",
                "common-id": "ASATT1234567",
                "connection-type": "roadm-line",
                "service-a-end": {
                    "service-rate": "100",
                    "node-id": "ROADM-A1",
                    "service-format": "OC",
                    "clli": "SNJSCAMCJP8",
                    "tx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJP8_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Tx.ge-5/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJP8_000000.00_00",
                            "lgx-port-name": "LGX Back.3",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "rx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJP8_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Rx.ge-5/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJP8_000000.00_00",
                            "lgx-port-name": "LGX Back.4",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "optic-type": "gray"
                },
                "service-z-end": {
                    "service-rate": "100",
                    "node-id": "ROADM-C1",
                    "service-format": "OC",
                    "clli": "SNJSCAMCJT4",
                    "tx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJT4_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Tx.ge-1/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJT4_000000.00_00",
                            "lgx-port-name": "LGX Back.29",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "rx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJT4_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Rx.ge-1/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJT4_000000.00_00",
                            "lgx-port-name": "LGX Back.30",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "optic-type": "gray"
                },
                "due-date": "2016-11-28T00:00:01Z",
                "operator-contact": "pw1234"
                }
        }
        response = self.trpceClient.createService(data)
        if not response.isSucceeded():
            self.logError(str(response.code)+" | " +response.content)
            return False

        success = self.assertIn('PCE calculation in progress',
                      response.data['output']['configuration-response-common']['response-message'])
  
        return success
        
    def test4GetOcService1(self, retries=1, delayForRetries=10):
        while retries>0:
            success = False
            response = self.trpceClient.getService('serviceoc1')
            if response.isSucceeded():
            
                success = self.assertEqual(
                    response.data['services'][0]['administrative-state'], 'inService')
                if not success and retries >0:
                    print("service still not with administrative-state inServerice (state="+response.data['services'][0]['administrative-state']+"). waiting...")
                success &= self.assertEqual(
                    response.data['services'][0]['service-name'], 'serviceoc1')
                success &= self.assertEqual(
                    response.data['services'][0]['connection-type'], 'service')
                success &= self.assertEqual(
                    response.data['services'][0]['lifecycle-state'], 'planned')
                if not success and retries >0:
                    print("service still not with lifecycle-state inServerice (state="+response.data['services'][0]['lifecycle-state']+"). waiting...")

                if success:
                    break
            else:
                print("service not available: responsecode: "+str(response.code))
            retries-=1
            time.sleep(delayForRetries)
            if retries>0:
                print("service still not with state inServerice. waiting...")

        if not success:
            self.logError(str(response.code)+" | "+ response.content)
            return False
        return True

    def test4CheckConnections(self):
         #check_xc1_ROADMA
        if self.config.isRemoteEnabled():
            response = self.sdncClient.getNodeData("ROADM-A1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG2-TTP-TXRX-1")
        else:
            response = self.trpceClient.getNodeData("ROADM-A1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG2-TTP-TXRX-1")
        if not response.isSucceeded():
            return False
        # the following statement replaces self.assertDictContainsSubset deprecated in python 3.2
        success = self.assertDictEqual(
            dict({
                'connection-name': 'SRG1-PP1-TXRX-DEG2-TTP-TXRX-1',
                'opticalControlMode': 'gainLoss',
                'target-output-power': -3.0
            }, **response.data['roadm-connections'][0]),
            response.data['roadm-connections'][0]
        )
        success &= self.assertDictEqual(
            {'src-if': 'SRG1-PP1-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['source'])
        success &= self.assertDictEqual(
            {'dst-if': 'DEG2-TTP-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['destination'])
        
        if not success:
            return False
        time.sleep(7)
        #check_xc1_ROADMC
        if self.config.isRemoteEnabled():
            response = self.sdncClient.getNodeData("ROADM-C1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG1-TTP-TXRX-1")
        else:
            response = self.trpceClient.getNodeData("ROADM-C1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG1-TTP-TXRX-1")
        if not response.isSucceeded():
            return False
        # the following statement replaces self.assertDictContainsSubset deprecated in python 3.2
        success = self.assertDictEqual(
            dict({
                'connection-name': 'SRG1-PP1-TXRX-DEG1-TTP-TXRX-1',
                'opticalControlMode': 'gainLoss',
                'target-output-power': -3.0
            }, **response.data['roadm-connections'][0]),
            response.data['roadm-connections'][0]
        )
        success &= self.assertDictEqual(
            {'src-if': 'SRG1-PP1-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['source'])
        success &= self.assertDictEqual(
            {'dst-if': 'DEG1-TTP-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['destination'])
        if not response.isSucceeded():
            return False

        return True
 def test4CreateOcService1(self):
        data = {"input": {
                "sdnc-request-header": {
                    "request-id": "e3028bae-a90f-4ddd-a83f-cf224eba0e58",
                    "rpc-action": "service-create",
                    "request-system-id": "appname",
                    "notification-url": "http://localhost:8585/NotificationServer/notify"
                },
                "service-name": "serviceoc1",
                "common-id": "ASATT1234567",
                "connection-type": "roadm-line",
                "service-a-end": {
                    "service-rate": "100",
                    "node-id": "ROADM-A1",
                    "service-format": "OC",
                    "clli": "SNJSCAMCJP8",
                    "tx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJP8_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Tx.ge-5/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJP8_000000.00_00",
                            "lgx-port-name": "LGX Back.3",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "rx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJP8_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Rx.ge-5/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJP8_000000.00_00",
                            "lgx-port-name": "LGX Back.4",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "optic-type": "gray"
                },
                "service-z-end": {
                    "service-rate": "100",
                    "node-id": "ROADM-C1",
                    "service-format": "OC",
                    "clli": "SNJSCAMCJT4",
                    "tx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJT4_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Tx.ge-1/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJT4_000000.00_00",
                            "lgx-port-name": "LGX Back.29",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "rx-direction": {
                        "port": {
                            "port-device-name": "ROUTER_SNJSCAMCJT4_000000.00_00",
                            "port-type": "router",
                            "port-name": "Gigabit Ethernet_Rx.ge-1/0/0.0",
                            "port-rack": "000000.00",
                            "port-shelf": "00"
                        },
                        "lgx": {
                            "lgx-device-name": "LGX Panel_SNJSCAMCJT4_000000.00_00",
                            "lgx-port-name": "LGX Back.30",
                            "lgx-port-rack": "000000.00",
                            "lgx-port-shelf": "00"
                        }
                    },
                    "optic-type": "gray"
                },
                "due-date": "2016-11-28T00:00:01Z",
                "operator-contact": "pw1234"
                }
        }
        response = self.trpceClient.createService(data)
        if not response.isSucceeded():
            self.logError(str(response.code)+" | " +response.content)
            return False

        success = self.assertIn('PCE calculation in progress',
                      response.data['output']['configuration-response-common']['response-message'])
  
        return success
        
    def test4GetOcService1(self, retries=1, delayForRetries=10):
        while retries>0:
            success = False
            response = self.trpceClient.getService('serviceoc1')
            if response.isSucceeded():
            
                success = self.assertEqual(
                    response.data['services'][0]['administrative-state'], 'inService')
                if not success and retries >0:
                    print("service still not with administrative-state inServerice (state="+response.data['services'][0]['administrative-state']+"). waiting...")
                success &= self.assertEqual(
                    response.data['services'][0]['service-name'], 'serviceoc1')
                success &= self.assertEqual(
                    response.data['services'][0]['connection-type'], 'service')
                success &= self.assertEqual(
                    response.data['services'][0]['lifecycle-state'], 'planned')
                if not success and retries >0:
                    print("service still not with lifecycle-state inServerice (state="+response.data['services'][0]['lifecycle-state']+"). waiting...")

                if success:
                    break
            else:
                print("service not available: responsecode: "+str(response.code))
            retries-=1
            time.sleep(delayForRetries)
            if retries>0:
                print("service still not with state inServerice. waiting...")

        if not success:
            self.logError(str(response.code)+" | "+ response.content)
            return False
        return True

    def test4CheckConnections(self):
         #check_xc1_ROADMA
        if self.config.isRemoteEnabled():
            response = self.sdncClient.getNodeData("ROADM-A1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG2-TTP-TXRX-1")
        else:
            response = self.trpceClient.getNodeData("ROADM-A1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG2-TTP-TXRX-1")
        if not response.isSucceeded():
            return False
        # the following statement replaces self.assertDictContainsSubset deprecated in python 3.2
        success = self.assertDictEqual(
            dict({
                'connection-name': 'SRG1-PP1-TXRX-DEG2-TTP-TXRX-1',
                'opticalControlMode': 'gainLoss',
                'target-output-power': -3.0
            }, **response.data['roadm-connections'][0]),
            response.data['roadm-connections'][0]
        )
        success &= self.assertDictEqual(
            {'src-if': 'SRG1-PP1-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['source'])
        success &= self.assertDictEqual(
            {'dst-if': 'DEG2-TTP-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['destination'])
        
        if not success:
            return False
        time.sleep(7)
        #check_xc1_ROADMC
        if self.config.isRemoteEnabled():
            response = self.sdncClient.getNodeData("ROADM-C1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG1-TTP-TXRX-1")
        else:
            response = self.trpceClient.getNodeData("ROADM-C1", 
                "/org-openroadm-device:org-openroadm-device/roadm-connections/SRG1-PP1-TXRX-DEG1-TTP-TXRX-1")
        if not response.isSucceeded():
            return False
        # the following statement replaces self.assertDictContainsSubset deprecated in python 3.2
        success = self.assertDictEqual(
            dict({
                'connection-name': 'SRG1-PP1-TXRX-DEG1-TTP-TXRX-1',
                'opticalControlMode': 'gainLoss',
                'target-output-power': -3.0
            }, **response.data['roadm-connections'][0]),
            response.data['roadm-connections'][0]
        )
        success &= self.assertDictEqual(
            {'src-if': 'SRG1-PP1-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['source'])
        success &= self.assertDictEqual(
            {'dst-if': 'DEG1-TTP-TXRX-nmc-1'},
            response.data['roadm-connections'][0]['destination'])
        if not response.isSucceeded():
            return False

        return True
