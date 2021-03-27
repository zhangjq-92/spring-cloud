package com.example.cloud.service;

import com.example.cloud.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findByUserName(String userName);

    List<Permission> findByUserId(Long userId);

    List<String> findPermissionsCodeByUserId(Long userId);
}
