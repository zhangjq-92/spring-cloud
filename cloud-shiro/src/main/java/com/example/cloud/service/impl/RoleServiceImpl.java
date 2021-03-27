package com.example.cloud.service.impl;

import com.example.cloud.mapper.RoleMapper;
import com.example.cloud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
}
