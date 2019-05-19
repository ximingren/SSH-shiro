package com.ximingren.realm;

import com.ximingren.entity.Permission;
import com.ximingren.entity.Role;
import com.ximingren.entity.User;
import com.ximingren.service.PermissionService;
import com.ximingren.service.RoleService;
import com.ximingren.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SampleRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthenticationException {
//        Long userId = TokenManager.getUserId();
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<Role> roles = userService.findRoles(username);
        Set<String> rolesString = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        for (Role r : roles) {
            rolesString.add(r.getName());
            Set<Permission> eachpermissions = roleService.findPermissions(r.getId());
            for (Permission permission : eachpermissions) {
                permissions.add(permission.getName());
            }
        }
        info.setRoles(rolesString);
        info.setStringPermissions(permissions);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        ShiroToken token=authenticationToken;
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(username);
        if (null == user) {
            throw new AccountException("帐号密码不正确!");
        } else if (user._0.equals(user.getStatus())) {
            throw new DisabledAccountException("帐号已经禁止登录！");
        } else {
            user.setLastLoginTime(new Date());
            userService.updateLoginTime(user);
        }
//TODO ByteSource
        return new SimpleAuthenticationInfo(user, user.getPswd(), ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
    }
}
