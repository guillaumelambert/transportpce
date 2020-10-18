package com.orange.onap.tpce.manager.helper;

public class DeviceHelper {

    public static final String NETWORK_ID = "openroadm-topology";
    public static final String NETWORK_TYPE = "org-openroadm-common-network:openroadm-common-network";

    public static final String NODE_ID = "roadmc221-DEG2";
    public static final String NODE_ID_2 = "roadmc221-DEG1";
    public static final String NODE_TYPE = "DEGREE";
    public static final String SUPPORTING_NETWORK_REF = "openroadm-network";
    public static final String SUPPORTING_NODE_REF = "roadmc221";
    public static final String TP_ID = "DEG2-CTP-TXRX";
    public static final String TP_TYPE = "DEGREE-TXRX-CTP";
    /*links for VOWL */
    public static final String VOWL_LINK_ID = "roadmc221-DEG1-DEG1-CTP-TXRXtoroadmc221-DEG2-DEG2-CTP-TXRX";
    public static final String VOWL_SOURCE_NODE = "roadmc221-DEG1";
    public static final String VOWL_DESTINATION_NODE = "roadmc221-DEG2";
    /* Link parsing test consts */
    public static final String LINK_ID = "roadma21-DEG2-DEG2-TTP-TXRXtoroadmb21-DEG1-DEG1-TTP-TXRX";
    public static final String SOURCE_NODE = "roadma21-DEG2";
    public static final String SOURCE_TP = "DEG2-TTP-TXRX";
    public static final String LINK_TYPE = "ROADM-TO-ROADM";
    public static final String DESTINATION_NODE = "roadmb21-DEG1";
    public static final String DESTINATION_TP = "DEG1-TTP-TXRX";
    public static final String OPPOSITE_LINK_ID = "roadmb21-DEG1-DEG1-TTP-TXRXtoroadma21-DEG2-DEG2-TTP-TXRX";

    public static final Integer WAVE_LENGTH_VALUE = 94;

    public static final String DEGREE_NODE_1 = buildNode(NODE_ID, NODE_TYPE, SUPPORTING_NETWORK_REF,
            SUPPORTING_NODE_REF);

    public static final String DEGREE_NODE_2 = buildNode(NODE_ID_2, NODE_TYPE,
            SUPPORTING_NETWORK_REF,
            SUPPORTING_NODE_REF);

    public static final String LINK = buildLink(LINK_ID, SOURCE_NODE, DESTINATION_NODE,
            OPPOSITE_LINK_ID);

    public static final String NETWORK_CONTENT = buildNetwork(new String[]{DEGREE_NODE_1, DEGREE_NODE_2},
            new String[]{LINK});

    public static String buildNode(String nodeId, String nodeType, String supportingNetwork, String supportingNode) {
        String node = "                {" +
                "                    \"node-id\": \"" + nodeId + "\"," +
                "                    \"termination-point\": [" +
                "                        {" +
                "                            \"tp-id\": \"" + TP_ID + "\"," +
                "                            \"tp-type\": \"" + TP_TYPE + "\"" +
                "                        }" +
                "                    ]," +
                "                    \"srg-attributes\": {" +
                "                        \"degree-number\": 1, " +
                "                        \"available-wavelengths\": [" +
                "                            {" +
                "                                \"index\": " + WAVE_LENGTH_VALUE +
                "                            }" +
                "                        ]" +
                "                    }," +
                "                    \"node-type\": \"" + nodeType + "\"," +
                "                    \"supporting-node\": " +
                "                        {" +
                "                            \"network-ref\": \"" + supportingNetwork + "\"," +
                "                            \"node-ref\": \"" + supportingNode + "\"" +
                "                        }" +
                "                }";

        return node;
    }

    public static String buildLink(String linkId, String sourceNode, String dest_node, String oppLink) {
        String link = "{" +
                "                    \"link-id\": \"" + linkId + "\"," +
                "                    \"source\": {" +
                "                        \"source-node\": \"" + sourceNode + "\"," +
                "                        \"source-tp\": \"" + SOURCE_TP + "\"" +
                "                    }," +
                "                    \"link-type\": \"" + LINK_TYPE + "\"," +
                "                    \"destination\": {" +
                "                        \"dest-node\": \"" + dest_node + "\"," +
                "                        \"dest-tp\": \"" + DESTINATION_TP + "\"" +
                "                    }," +
                "                    \"opposite-link\": \"" + oppLink + "\"" +
                "                }";

        return link;
    }

    public static String buildNetwork(String[] nodes, String[] links) {
        StringBuilder network = new StringBuilder();
        network.append("     {  \"network-id\": \"" + NETWORK_ID + "\"");

        network.append(",    \"node\": [");
        for (String node : nodes) {
            network.append(node + ",");
        }
        network.deleteCharAt(network.length()-1);
        network.append("]");


        network.append(", \"network-types\": { \"" + NETWORK_TYPE + "\": {}   }");

        network.append(",      \"link\": [");
        for (String link : links) {
            network.append(link + ",");
        }
        network.deleteCharAt(network.length()-1);
        network.append("]");

        network.append("}");

        return network.toString();
    }



}
