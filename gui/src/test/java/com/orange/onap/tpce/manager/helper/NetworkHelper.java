package com.orange.onap.tpce.manager.helper;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class NetworkHelper {

    private static final String NETWORK_PREFIX = "{" +
            "    \"network\": [";

    private static final String NETWORK_POSTFIX = " ]" +
            "}";


    public static String getTopologyContent() {
        return  getNetworkMockFileContent();//NetworkHelper.NETWORK_PREFIX + DeviceHelper.NETWORK_CONTENT + NetworkHelper.NETWORK_POSTFIX;
    }

    public static String getClliContent() {
        return getNetworkMockFileContent();//NetworkHelper.NETWORK_PREFIX + ClliHelper.NETWORK_CONTENT + NetworkHelper.NETWORK_POSTFIX;
    }

    public static String getRoadMContent() {
        return getNetworkMockFileContent();//NetworkHelper.NETWORK_PREFIX + RoadMHelper.NETWORK_CONTENT + NetworkHelper.NETWORK_POSTFIX;
    }

    public static String getAllContent() {
        return NetworkHelper.NETWORK_PREFIX
                + ClliHelper.NETWORK_CONTENT + " , "
                + RoadMHelper.NETWORK_CONTENT + " , "
                + DeviceHelper.NETWORK_CONTENT
                + NetworkHelper.NETWORK_POSTFIX;
    }

    public static String getNetworkMockFileContent() {
        String content = "";
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("NetWorkList.json")).getFile());
            //Read File Content
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
        }
        return content;
    }
}
