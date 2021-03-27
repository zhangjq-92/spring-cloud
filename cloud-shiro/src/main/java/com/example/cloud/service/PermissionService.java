package com.example.cloud.service;

import com.example.cloud.entity.Permission;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermissionService {

    List<Permission> findByUserName(String userName);

    List<Permission> findByUserId(Long userId);

    List<String> findPermissionsCodeByUserId(Long userId);

    PageInfo<Permission> findByPage(int currentPage, int pageSize);
}
