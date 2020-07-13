#
# integration test for transportpce with ONAP SDNC
#
# mounting test
# check if mount and unmount state is passed correctly to trpce container
# conditions:
#   * stable websocket connection
#   * 
# szenario 1:
#   * everything running
#   * mount roadmA sim on SDNC side
#   * check connection state on SDNC side
#   * check logs on trpce side
# szenario 2:
#   * everything running
#   * unmount roadmA sim on SDNC side
#   * check connection state on SDNC side
#   * check logs on trpce side


class MountingTest:

    def __init__(self, sdncClient, trpceClient, dockerExec):

        self.sdncClient = sdncClient
        self.trpceClient = trpceClient
        self.dockerExec = dockerExec

    

    def test1(self):

        pass

    def test2(self):
        pass