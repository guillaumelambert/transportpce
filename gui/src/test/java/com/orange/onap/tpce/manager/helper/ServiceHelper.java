package com.orange.onap.tpce.manager.helper;

import org.springframework.stereotype.Service;

@Service
public class ServiceHelper {

    private static final String SERVICE_LIST =
            "{" +
                    "    \"services\": [" +
                    "        {" +
                    "            \"service-name\": \"service1\"," +
                    "            \"service-z-end\": {" +
                    "                \"service-format\": \"OTU\"," +
                    "                \"node-id\": \"XPDR-C1\"," +
                    "                \"service-rate\": 100" +
                    "            }," +
                    "            \"administrative-state\": \"inService\"," +
                    "            \"connection-type\": \"service\"," +
                    "            \"service-a-end\": {" +
                    "                \"optic-type\": \"gray\"," +
                    "                \"service-format\": \"OTU\"," +
                    "                \"node-id\": \"XPDR-A1\"," +
                    "                \"service-rate\": 100" +
                    "            }," +
                    "            " +
                    "            \"service-layer\": \"wdm\"," +
                    "            \"lifecycle-state\": \"planned\"," +
                    "            \"operational-state\": \"inService\"" +
                    "        }" +
                    "    ]" +
                    "}";

    public static String getServiceContent() {
        return SERVICE_LIST;
    }
}
