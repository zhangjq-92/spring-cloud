package com.example.cloud.config;

import com.example.cloud.entity.TestUser;
import com.example.cloud.service.PermissionService;
import com.example.cloud.service.UserService;
import com.example.cloud.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo author = new SimpleAuthorizationInfo();
        TestUser testUser = (TestUser) SecurityUtils.getSubject().getPrincipal();
        List<String> permList = permissionService.findPermissionsCodeByUserId(testUser.getId());
        author.addStringPermissions(permList);
        return author;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行了认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        TestUser user = userService.findUserByName(token.getUsername());
        if (user == null){
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
