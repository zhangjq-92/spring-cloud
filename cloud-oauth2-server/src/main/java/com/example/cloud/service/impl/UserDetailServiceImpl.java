package com.example.cloud.service.impl;

import com.example.cloud.entity.Permission;
import com.example.cloud.entity.TestUser;
import com.example.cloud.service.PermissionService;
import com.example.cloud.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "permissionService")
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TestUser testUser = userService.findUserByName(s);
        if (testUser == null){
            return null;
        }
        List<String> permissionCodeList = permissionService.findPermissionsCodeByUserId(testUser.getId());
        String[] permissionCodeArray = permissionCodeList.toArray(new String[permissionCodeList.size()]);
        UserDetails userDetails = User.withUsername(testUser.getUserName()).password(testUser.getPassword()).authorities(permissionCodeArray).build();
        return userDetails;
    }
}
