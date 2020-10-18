package com.orange.onap.tpce.manager.common.security;

import com.orange.onap.tpce.manager.common.security.aaf.AAFAuthorizationService;
import org.onap.aaf.cadi.Permission;
import org.onap.aaf.cadi.util.Split;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("ignore")
public class AafBasedUserDetailsService implements AuthenticationUserDetailsService {

    static Logger logger = LoggerFactory.getLogger(AafBasedUserDetailsService.class);


    @Autowired
    AAFAuthorizationService aafService;

    @Override
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        logger.info("loadUserByUsername({})", token.getPrincipal());
        if (token == null || token.getPrincipal() == null || token.getPrincipal().equals("")) {
            throw new UsernameNotFoundException("user  is missing");
        }

        String userId = token.getPrincipal().toString();
        String aafUser = aafService.convertUserIdToAafFormat(userId);
        logger.info("AAF User id to be authorized bu AAF { " + aafUser + "}");

        List<Permission> permissionList = aafService.getUserPermissions(aafUser);

        Set<SimpleGrantedAuthority> authorities =
                permissionList.stream()
                        //filter out permission with different format
                        .filter(permission -> {
                            String[] permA = Split.splitTrim('|', permission.getKey());
                            return (permA.length > 2);
                        })
                        //get the first part of permission which is permission type
                        //and create spring authority
                        .map(permission -> {
                            String[] permA = Split.splitTrim('|', permission.getKey());
                            return new SimpleGrantedAuthority(permA[0]);
                        })
                        //user take set of authority
                        .collect(Collectors.toSet());

        User user = new User(userId, "N/A", authorities);

        return user;
    }
}

