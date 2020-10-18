package com.orange.onap.tpce.manager.common.security.aaf;

import com.orange.onap.tpce.manager.common.security.aaf.client.custom.AAFConHttp;
import org.onap.aaf.cadi.CadiException;
import org.onap.aaf.cadi.LocatorException;
import org.onap.aaf.cadi.Permission;
import org.onap.aaf.cadi.PropAccess;
import org.onap.aaf.cadi.aaf.v2_0.AAFCon;
import org.onap.aaf.cadi.aaf.v2_0.AAFLurPerm;
import org.onap.aaf.cadi.config.Config;
import org.onap.aaf.cadi.principal.UnAuthPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Service
@Profile("ignore")
public class AAFAuthorizationService {

    private AAFLurPerm aafLur;

    public AAFAuthorizationService(@Value("${onap.aaf.url}") String url,
                                   @Value("${onap.aaf.user_fqi}") String user_fqi,
                                   @Value("${onap.aaf.user_pass}") String user_pass,
                                   @Value("${onap.aaf.aaf_id}") String aaf_id,
                                   @Value("${onap.aaf.aaf_password}") String aaf_password) throws CadiException, LocatorException {

        Properties props = System.getProperties();
        props.setProperty("cadi_latitude", "32.780140");
        props.setProperty("cadi_longitude", "-96.800451");
        props.setProperty("AFT_ENVIRONMENT", "AFTUAT");
        props.setProperty(Config.AAF_URL, url);
        props.setProperty(Config.AAF_USER_EXPIRES, Integer.toString(60000));   // 5 minutes for found items to live in cache
        props.setProperty(Config.AAF_HIGH_COUNT, Integer.toString(400));         // Maximum number of items in Cache);
        props.setProperty("user_fqi", user_fqi);
        props.setProperty("user_pass", user_pass);
        props.setProperty("aaf_id", aaf_id);
        props.setProperty("aaf_password", aaf_password);
        props.setProperty(Config.AAF_LOCATOR_PUBLIC_PORT, "31369");


        PropAccess myAccess = new PropAccess(props);


        /*
         * NOTE:  Do NOT CREATE new aafcon, aafLur and aafAuthn each transaction.  They are built to be
         * reused!
         *
         * This is why auth service is  a singleton.
         */
        AAFCon<?> con = new AAFConHttp(myAccess);
        System.out.println("instantiate aas conn..");
        System.out.printf("Service Port = %s\n", con.client().getURI().getPort());

        // AAFLur has pool of DME clients as needed, and Caches Client lookups
        aafLur = con.newLur();

    }

    public List<Permission> getUserPermissions(String onapUser) {

        List<Permission> allUserPermissions = null;

        try {

//                String id = "demo@people.osaaf.org";
            Principal fqi = new UnAuthPrincipal(onapUser);

            // Get All user permissions
            allUserPermissions = allAuthorization(fqi);
            System.out.printf("User %s has %s Permissions \n", fqi.getName(), allUserPermissions.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUserPermissions;

    }

    public String convertUserIdToAafFormat(String userId) {
        return userId + "@people.osaaf.org";

    }

    private List<Permission> allAuthorization(Principal fqi) {
        List<Permission> pond = new ArrayList<>();
        this.aafLur.fishAll(fqi, pond);
        return pond;
    }

}
