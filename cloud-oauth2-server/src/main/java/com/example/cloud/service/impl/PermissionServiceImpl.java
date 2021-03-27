package com.example.cloud.service.impl;

import com.example.cloud.entity.Permission;
import com.example.cloud.mapper.PermissionMapper;
import com.example.cloud.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByUserName(String userName) {
        return permissionMapper.findByUserName(userName);
    }

    @Override
    public List<Permission> findByUserId(Long userId) {
        return permissionMapper.findByUserId(userId);
    }

    @Override
    public List<String> findPermissionsCodeByUserId(Long userId) {
        List<Permission> permissions = this.findByUserId(userId);
        List<String> list = new ArrayList<>();
        permissions.forEach(c -> list.add(c.getCode()));
        return list;
    }
}
