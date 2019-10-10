package com.freshmel.security;

import com.freshmel.model.Customer;
import com.freshmel.model.User;
import com.freshmel.model.Vender;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class AppRealm extends JdbcRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        return super.doGetAuthenticationInfo(token);
    }

    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new HashSet<>();

        if (principals.isEmpty()) {
            System.out.println("Given principals to authorize are null");
            return null;
        }

        int username = (Integer) principals.getPrimaryPrincipal();
        final User user = new Customer();

        if (user == null) {
            System.out.println("no account found for user with username" + username);
            return null;
        }

        if (user instanceof Vender) {
            roles.add(AppSession.VENDOR_ROLE);
        } else if (user instanceof Customer) {
            roles.add(AppSession.CUSTOMER_ROLE);
        }

        return new SimpleAuthorizationInfo(roles);
    }

//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//
//        return super.doGetAuthorizationInfo(principals);
//    }
}

