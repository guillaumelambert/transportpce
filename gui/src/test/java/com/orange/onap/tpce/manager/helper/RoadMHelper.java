package com.orange.onap.tpce.manager.helper;

public class RoadMHelper {

    public static final String NETWORK_TYPE = "org-openroadm-common-network:openroadm-common-network";
    public static final String NETWORK_ID = "openroadm-network";

    public static final String NODE_ID = "roadmc221";
    public static final String NODE_TYPE = "ROADM";
    public static final String SUPPORTING_NETWORK_REF = "clli-network";
    public static final String SUPPORTING_NODE_REF = "NodeC";

    public static final String NETWORK_CONTENT =

            "      {" +
                    "        \"network-id\": \"" + NETWORK_ID + "\"," +
                    "        \"network-types\": {" +
                    "                \"" + NETWORK_TYPE + "\": {}" +
                    "        }," +
                    "        \"node\": [" +
                    "             {" +
                    "                  \"node-id\": \"" + NODE_ID + "\"," +
                    "                  \"supporting-node\": {" +
                    "                       \"network-ref\": \"" + SUPPORTING_NETWORK_REF + "\"," +
                    "                       \"node-ref\": \"" + SUPPORTING_NODE_REF + "\"" +
                    "                  }," +
                    "                  \"node-type\": \"" + NODE_TYPE + "\"," +
                    "                  \"vendor\": \"vendorA\"," +
                    "                  \"ip\": \"127.0.0.11\"," +
                    "                  \"model\": \"model2\"" +
                    "             }" +
                    "        ]" +
                    "}";
}
