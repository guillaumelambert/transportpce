/**
 * r * ============LICENSE_START====================================================
 * org.onap.aaf
 * ===========================================================================
 * Copyright (c) 2018 AT&T Intellectual Property. All rights reserved.
 * ===========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END====================================================
 */

package com.orange.onap.tpce.manager.common.security.aaf.client.custom;

import locate.v1_0.Endpoint;
import org.onap.aaf.cadi.Locator;
import org.onap.aaf.cadi.*;
import org.onap.aaf.cadi.Locator.Item;
import org.onap.aaf.cadi.aaf.v2_0.AAFCon;
import org.onap.aaf.cadi.client.AbsTransferSS;
import org.onap.aaf.cadi.client.Rcli;
import org.onap.aaf.cadi.client.Retryable;
import org.onap.aaf.cadi.config.Config;
import org.onap.aaf.cadi.config.SecurityInfoC;
import org.onap.aaf.cadi.http.HBasicAuthSS;
import org.onap.aaf.cadi.http.HTokenSS;
import org.onap.aaf.cadi.http.HTransferSS;
import org.onap.aaf.cadi.http.HX509SS;
import org.onap.aaf.cadi.principal.BasicPrincipal;
import org.onap.aaf.cadi.principal.TaggedPrincipal;
import org.onap.aaf.cadi.routing.GreatCircle;
import org.onap.aaf.misc.env.APIException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import org.onap.aaf.cadi.http.HMangr;
//import org.onap.aaf.cadi.http.HRcli;

public class AAFConHttp extends AAFCon<HttpURLConnection> {
    private final HMangr hman;
    private Map<String, Rcli<HttpURLConnection>> clients = new ConcurrentHashMap<>();

    public AAFConHttp(Access access) throws CadiException, LocatorException {
        super(access, Config.AAF_URL, SecurityInfoC.instance(access, HttpURLConnection.class));
        hman = new HMangr(access, Config.loadLocator(si, access.getProperty(Config.AAF_URL, null)));
        System.out.println("myAAF");
        //System.out.println(si);
    }

    protected SecuritySetter<HttpURLConnection> bestSS(SecurityInfoC<HttpURLConnection> si) throws CadiException {
        return si.defSS;
    }

    public AAFConHttp(Access access, String tag) throws CadiException, LocatorException {
        super(access, tag, SecurityInfoC.instance(access, HttpURLConnection.class));
        bestSS(si);
        hman = new HMangr(access, Config.loadLocator(si, access.getProperty(tag, tag/*try the content itself*/)));
    }

    public AAFConHttp(Access access, String urlTag, SecurityInfoC<HttpURLConnection> si) throws CadiException, LocatorException {
        super(access, urlTag, si);
        bestSS(si);
        hman = new HMangr(access, Config.loadLocator(si, access.getProperty(urlTag, null)));
    }

    public AAFConHttp(Access access, Locator<URI> locator) throws CadiException, LocatorException {
        super(access, Config.AAF_URL, SecurityInfoC.instance(access, HttpURLConnection.class));
        bestSS(si);
        hman = new HMangr(access, locator);
    }

    public AAFConHttp(Access access, Locator<URI> locator, SecurityInfoC<HttpURLConnection> si) throws CadiException, LocatorException, APIException {
        super(access, Config.AAF_URL, si);
        bestSS(si);
        hman = new HMangr(access, locator);
    }

    public AAFConHttp(Access access, Locator<URI> locator, SecurityInfoC<HttpURLConnection> si, String tag) throws CadiException, LocatorException, APIException {
        super(access, tag, si);
        bestSS(si);
        hman = new HMangr(access, locator);
    }

    private AAFConHttp(AAFCon<HttpURLConnection> aafcon, String url) throws LocatorException {
        super(aafcon);
        si = aafcon.securityInfo();
        hman = new HMangr(aafcon.access, Config.loadLocator(si, url));
    }

    @Override
    public AAFCon<HttpURLConnection> clone(String url) throws LocatorException {
        return new AAFConHttp(this, url);
    }

    /* (non-Javadoc)
     * @see org.onap.aaf.cadi.aaf.v2_0.AAFCon#basicAuth(java.lang.String, java.lang.String)
     */
    @Override
    public SecuritySetter<HttpURLConnection> basicAuth(String user, String password) throws CadiException {
        if (password.startsWith("enc:")) {
            try {
                password = access.decrypt(password, true);
            } catch (IOException e) {
                throw new CadiException("Error decrypting password", e);
            }
        }
        try {
            return new HBasicAuthSS(si, user, password);
        } catch (IOException e) {
            throw new CadiException("Error creating HBasicAuthSS", e);
        }
    }

    public SecuritySetter<HttpURLConnection> x509Alias(String alias) throws CadiException {
        try {
            return set(new HX509SS(alias, si));
        } catch (Exception e) {
            throw new CadiException("Error creating X509SS", e);
        }
    }

    /* (non-Javadoc)
     * @see org.onap.aaf.cadi.aaf.v2_0.AAFCon#rclient(java.net.URI, org.onap.aaf.cadi.SecuritySetter)
     */
    @Override
    protected Rcli<HttpURLConnection> rclient(URI ignoredURI, SecuritySetter<HttpURLConnection> ss) throws CadiException {
        if (hman.loc == null) {
            throw new CadiException("No Locator set in AAFConHttp");
        }
        try {
            System.out.printf("rclient -- %s %s %s", hman, hman.loc.best(), ss);
            // Added by Waly
            //AAFLItem myItem = (AAFLItem)hman.loc.best();
            //System.out.printf("the extracted URI = %s",myItem.uri);
            return new HRcli(hman, hman.loc.best(), ss);
        } catch (Exception e) {
            throw new CadiException(e);
        }
    }

    @Override
    public Rcli<HttpURLConnection> rclient(Locator<URI> loc, SecuritySetter<HttpURLConnection> ss) throws CadiException {
        try {
            HMangr newHMan = new HMangr(access, loc);
            return new HRcli(newHMan, newHMan.loc.best(), ss);
        } catch (Exception e) {
            throw new CadiException(e);
        }
    }

    @Override
    public AbsTransferSS<HttpURLConnection> transferSS(TaggedPrincipal principal) {
        return new HTransferSS(principal, app, si);
    }

    /* (non-Javadoc)
     * @see org.onap.aaf.cadi.aaf.v2_0.AAFCon#basicAuthSS(java.security.Principal)
     */
    @Override
    public SecuritySetter<HttpURLConnection> basicAuthSS(BasicPrincipal principal) throws CadiException {
        try {
            return new HBasicAuthSS(principal, si);
        } catch (IOException e) {
            throw new CadiException("Error creating HBasicAuthSS", e);
        }
    }

    @Override
    public SecuritySetter<HttpURLConnection> tokenSS(final String client_id, final String accessToken) throws CadiException {
        try {
            return new HTokenSS(si, client_id, accessToken);
        } catch (IOException e) {
            throw new CadiException(e);
        }
    }

    public HMangr hman() {
        return hman;
    }

    @Override
    public <RET> RET best(Retryable<RET> retryable) throws LocatorException, CadiException, APIException {

        return hman.best(si.defSS, retryable);
    }

    /* (non-Javadoc)
     * @see org.onap.aaf.cadi.aaf.v2_0.AAFCon#bestForUser(org.onap.aaf.cadi.SecuritySetter, org.onap.aaf.cadi.client.Retryable)
     */
    @Override
    public <RET> RET bestForUser(GetSetter getSetter, Retryable<RET> retryable) throws LocatorException, CadiException, APIException {
        return hman.best(getSetter.get(this), retryable);
    }

    /* (non-Javadoc)
     * @see org.onap.aaf.cadi.aaf.v2_0.AAFCon#initURI()
     */
    @Override
    protected URI initURI() {
        // Outcome of this method is ignored  !!!!!
        System.out.println("AAFConHttp --- my initURI ");
        try {
            Item item = hman.loc.best();
            if (item != null) {
                System.out.println("AAFConHttp --- item!=null ");
                //System.out.println(((AAFLItem)item).uri);
                System.out.println(item);

                URI serviceURI = null;

                try {
                    serviceURI = new URI("https://aaf-service.onap:30247");
                    System.out.println(serviceURI);
                    //serviceURI = new URI(serviceURI.getScheme(), serviceURI.getUserInfo(), serviceURI.getHost(),30247,
                    //		serviceURI.getPath(), serviceURI.getQuery(), serviceURI.getFragment());
                    //System.out.printf("URI After update",serviceURI);
                    return serviceURI;
                } catch (URISyntaxException e) {
                    System.out.printf("Updating URI port failed:", e);

                }


                //return hman.loc.get(item);
            }
        } catch (LocatorException e) {
            access.log(e, "Error in AAFConHttp obtaining initial URI");
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.onap.aaf.cadi.aaf.v2_0.AAFCon#setInitURI(java.lang.String)
     */
    @Override
    protected void setInitURI(String uriString) {
        // Using Locator, not URLString, which is mostly for DME2
    }


    @Override
    /**
     * Use this call to get the appropriate client based on configuration (HTTP, future)
     * using default AAF API Version
     *
     * @param apiVersion
     * @return
     * @throws CadiException
     */
    public Rcli<HttpURLConnection> client() throws CadiException {
        return client(apiVersion);
    }

    @Override
    /**
     * Use this call to get the appropriate client based on configuration (HTTP, future)
     *
     * @param apiVersion
     * @return
     * @throws CadiException
     */
    public Rcli<HttpURLConnection> client(final String apiVersion) throws CadiException {
        Rcli<HttpURLConnection> client = clients.get(apiVersion);
        System.out.println("AAFConHttp --- Rcli 0 ");
        if (client == null) {
            client = rclient(initURI(), si.defSS);
            //System.out.printf("client = ",client);
            client.apiVersion(apiVersion)
                    .readTimeout(connTimeout);

            clients.put(apiVersion, client);


        }
        return client;
    }


    protected static class AAFLItem implements Item {
        private Iterator<EP> iter;
        private URI uri;
        private EP ep;

        public AAFLItem(Iterator<EP> iter, EP ep) {
            this.iter = iter;
            this.ep = ep;
            uri = ep.uri;
        }

        private static EP next(Iterator<EP> iter) {
            EP ep = null;
            while (iter.hasNext() && (ep == null || !ep.valid)) {
                ep = iter.next();
            }
            return ep;
        }

        public String toString() {
            return ep == null ? "Locator Item Invalid" : ep.toString();
        }
    }

    protected static class EP implements Comparable<EP> {
        private URI uri;
        private final double distance;
        private boolean valid;

        public EP(final Endpoint ep, double latitude, double longitude) throws URISyntaxException {
            uri = new URI(ep.getProtocol(), null, ep.getHostname(), ep.getPort(), null, null, null);
            distance = GreatCircle.calc(latitude, longitude, ep.getLatitude(), ep.getLongitude());
            valid = true;
        }

        public void invalid() {
            valid = false;
        }

        @Override
        public int compareTo(EP o) {
            if (distance < o.distance) {
                return -1;
            } else if (distance > o.distance) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return distance + ": " + uri + (valid ? " valid" : " invalidate");
        }
    }


}
