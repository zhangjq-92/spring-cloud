package com.example.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloud.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByUserName(@Param("userName") String userName);

    List<Permission> findByUserId(@Param("userId") Long userId);
}
