package com.orange.onap.tpce.manager.helper;

public class ClliHelper {

    public static final String NETWORK_ID = "clli-network";
    public static final String NETWORK_TYPE = "org-openroadm-clli-network:clli-network";

    public static final String NODE_ID = "NodeA";
    public static final String CLLI_NAME = "NodeA";
    public static final String NETWORK_CONTENT =

            "       {" +
                    "        \"network-id\": \"" + NETWORK_ID + "\"," +
                    "        \"network-types\": {" +
                    "               \"" + NETWORK_TYPE + "\": {}" +
                    "        }," +
                    "        \"node\": [" +
                    "              {" +
                    "                  \"node-id\": \"" + NODE_ID + "\"," +
                    "                 \"clli\": \"" + CLLI_NAME + "\"" +
                    "             }" +
                    "        ]" +
                    "}";
}
