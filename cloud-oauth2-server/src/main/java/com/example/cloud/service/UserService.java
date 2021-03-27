package com.example.cloud.service;


import com.example.cloud.entity.TestUser;

public interface UserService {

    TestUser findUserByName(String name);
}

