#!/usr/bin/env python

##############################################################################
#Copyright (c) 2017 Orange, Inc. and others.  All rights reserved.
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, Version 2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
##############################################################################

import json
import os
import psutil
import requests
import signal
import shutil
import subprocess
import time
import unittest
import logging

class TransportGNPYtesting(unittest.TestCase):

    gnpy_process = None
    odl_process = None
    restconf_baseurl = "http://localhost:8181/restconf"

    @classmethod
    def __init_logfile(cls):
        if os.path.isfile("./transportpce_tests/gnpy.log"):
            os.remove("transportpce_tests/gnpy.log")

    @classmethod
    def __start_odl(cls):
        executable = "../karaf/target/assembly/bin/karaf"
        with open('transportpce_tests/odl.log', 'w') as outfile:
            cls.odl_process = subprocess.Popen(
                ["bash", executable, "server"], stdout=outfile,
                stdin=open(os.devnull))

    @classmethod
    def setUpClass(cls):
        cls.__start_odl()
        time.sleep(30)

    @classmethod
    def tearDownClass(cls):
        for child in psutil.Process(cls.odl_process.pid).children():
            child.send_signal(signal.SIGINT)
            child.wait()
        cls.odl_process.send_signal(signal.SIGINT)
        cls.odl_process.wait()

    def setUp(self):
        time.sleep(2)

    #Mount the different topologies
    def test_01_connect_clliNetwork(self):
        url = ("{}/config/ietf-network:networks/network/clli-network"
               .format(self.restconf_baseurl))
        topo_clliNet_file = "sample_configs/gnpy/clliNetwork.json"
        if os.path.isfile(topo_clliNet_file):
            with open(topo_clliNet_file, 'r') as clli_net:
                body = clli_net.read()
        headers = {'content-type': 'application/json'}
        response = requests.request(
             "PUT", url, data=body, headers=headers,
             auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        time.sleep(3)

    def test_02_connect_openroadmNetwork(self):
        url = ("{}/config/ietf-network:networks/network/openroadm-network"
               .format(self.restconf_baseurl))
        topo_ordNet_file = "sample_configs/gnpy/openroadmNetwork.json"
        if os.path.isfile(topo_ordNet_file):
            with open(topo_ordNet_file, 'r') as ord_net:
                body = ord_net.read()
        headers = {'content-type': 'application/json'}
        response = requests.request(
             "PUT", url, data=body, headers=headers,
             auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        time.sleep(3)

    def test_03_connect_openroadmTopology(self):
        url = ("{}/config/ietf-network:networks/network/openroadm-topology"
               .format(self.restconf_baseurl))
        topo_ordTopo_file = "sample_configs/gnpy/openroadmTopology.json"
        if os.path.isfile(topo_ordTopo_file):
            with open(topo_ordTopo_file, 'r') as ord_topo:
                body = ord_topo.read()
        headers = {'content-type': 'application/json'}
        response = requests.request(
             "PUT", url, data=body, headers=headers,
             auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        time.sleep(3)

    #Test the gnpy
    def test_04_path_computation_xpdr_bi(self):
        url = ("{}/operations/transportpce-pce:path-computation-request"
              .format(self.restconf_baseurl))
        body = {"input": {
                "service-name": "service-1",
                "resource-reserve": "true",
                "pce-metric": "hop-count",
                "service-handler-header": {
                    "request-id": "request-1"
                },
                "service-a-end": {
                    "node-id": "XPDRA",
                    "service-rate": "100",
                    "clli": "nodeA"
                },
                "service-z-end": {
                    "node-id": "XPDRB",
                    "service-rate": "100",
                    "clli": "nodeB"
                }
            }
        }
        headers = {'content-type': 'application/json',
        "Accept": "application/json"}
        response = requests.request(
            "POST", url, data=json.dumps(body), headers=headers,
            auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        res = response.json()
        self.assertEqual(res['output']['gnpy-response'][0]['path-dir'], 'A-to-Z')
        self.assertEqual(res['output']['gnpy-response'][0]['feasibility'],True)
        self.assertEqual(res['output']['gnpy-response'][1]['path-dir'], 'Z-to-A')
        self.assertEqual(res['output']['gnpy-response'][1]['feasibility'],True)
        time.sleep(5)

    #Disconnect the different topology
    def test_05_disconnect_openroadmTopology(self):
        url = ("{}/config/ietf-network:networks/network/openroadm-topology"
               .format(self.restconf_baseurl))
        data = {}
        headers = {'content-type': 'application/json'}
        response = requests.request(
             "DELETE", url, data=json.dumps(data), headers=headers,
             auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        time.sleep(3)

    def test_06_disconnect_openroadmNetwork(self):
        #Config ROADMA
        url = ("{}/config/ietf-network:networks/network/openroadm-network"
               .format(self.restconf_baseurl))
        data = {}
        headers = {'content-type': 'application/json'}
        response = requests.request(
             "DELETE", url, data=json.dumps(data), headers=headers,
             auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        time.sleep(3)

    def test_07_disconnect_clliNetwork(self):
        url = ("{}/config/ietf-network:networks/network/clli-network"
               .format(self.restconf_baseurl))
        data = {}
        headers = {'content-type': 'application/json'}
        response = requests.request(
             "DELETE", url, data=json.dumps(data), headers=headers,
             auth=('admin', 'admin'))
        self.assertEqual(response.status_code, requests.codes.ok)
        time.sleep(3)

if __name__ == "__main__":
    #logging.basicConfig(filename='./transportpce_tests/log/response.log',filemode='w',level=logging.DEBUG)
    unittest.main(verbosity=2)
