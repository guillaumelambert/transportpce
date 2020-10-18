package com.orange.onap.tpce.manager.common.security;

import org.onap.portalsdk.core.onboarding.exception.CipherUtilException;
import org.onap.portalsdk.core.onboarding.util.CipherUtil;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class OnapCookieBasedPreAuthenticatedProcessingFilter
        extends AbstractPreAuthenticatedProcessingFilter {


    public static final String USER_ID = "UserId";


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {

        logger.info("request " + request.getRequestURI());
        return "admin";
        /*try {
            String userId = getUserIdFromCookie(request);
            return userId;
        } catch (CipherUtilException e) {
            throw new PreAuthenticatedCredentialsNotFoundException("Failed to get userId from cookies ");
        }*/
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }


    private String getUserIdFromCookie(HttpServletRequest request)
            throws CipherUtilException {
        String userId = "";
        Cookie userIdCookie = getCookie(request, USER_ID);
        if (userIdCookie != null) {
            final String cookieValue = userIdCookie.getValue();

            final String decryptionKey = "AGLDdG4D04BKm2IxIWEr8o==";
            userId = CipherUtil.decrypt(cookieValue, decryptionKey);
            System.out.println("getUserIdFromCookie: decrypted as { " + userId + "}");
        }
        return userId;
    }

    /**
     * Searches the request for the named cookie.
     *
     * @param request    HttpServletRequest
     * @param cookieName Name of desired cookie
     * @return Cookie if found; otherwise null.
     */
    private Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(cookieName))
                    return cookie;
        return null;
    }


}
