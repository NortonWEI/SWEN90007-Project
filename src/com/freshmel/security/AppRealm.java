package com.freshmel.security;

import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.dataMapper.VenderMapper;
import com.freshmel.model.Customer;
import com.freshmel.model.User;
import com.freshmel.model.Vender;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AppRealm extends JdbcRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info;

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        final String username = usernamePasswordToken.getUsername();
        final char[] passwordChar = usernamePasswordToken.getPassword();
        final String password = new String(passwordChar);

        User user = null;

        VenderMapper venderMapper = new VenderMapper();

        Vender vender = new Vender();
        vender.setEmail(username);
        vender.setPassword(password);

        try {
            user = venderMapper.findByEmailANDPassword(vender);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null) {
            CustomerMapper customerMapper = new CustomerMapper();
            Customer customer = new Customer();
            customer.setEmail(username);
            customer.setPassword(password);

            try {
                user = customerMapper.findByEmailANDPassword(customer);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (user == null) {
                return null;
            } else {
                info = new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(), this.getName());
            }
        } else {
            info = new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(), this.getName());
        }

        return info;
    }

    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new HashSet<>();

        if (principals.isEmpty()) {
            System.out.println("Given principals to authorize are null");
            return null;
        }

        String username = (String) principals.getPrimaryPrincipal();
        User user = null;

        VenderMapper venderMapper = new VenderMapper();

        try {
            user = venderMapper.findByEmail(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null) {
            CustomerMapper customerMapper = new CustomerMapper();
            try {
                user = customerMapper.findByEmail(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (user == null) {
                System.out.println("no account found for user with username" + username);
                return null;
            }
        }

        if (user instanceof Vender) {
            roles.add(AppSession.VENDOR_ROLE);
        } else if (user instanceof Customer) {
            roles.add(AppSession.CUSTOMER_ROLE);
        }

        return new SimpleAuthorizationInfo(roles);
    }
}

