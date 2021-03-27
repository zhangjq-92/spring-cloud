package com.example.cloud.service.impl;

import com.example.cloud.entity.TestUser;
import com.example.cloud.mapper.UserMapper;
import com.example.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public TestUser findUserByName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", name);
        List<TestUser> list = userMapper.selectByMap(map);
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
