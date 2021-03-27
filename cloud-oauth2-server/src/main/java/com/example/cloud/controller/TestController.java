package com.example.cloud.controller;

import com.example.cloud.entity.TestUser;
import com.example.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/user")
//    @PreAuthorize("hasAuthority('test_user')")
    public String haha(){
        return userService.findUserByName("user").toString();
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        TestUser testUser = new TestUser();
//        return userDetails.toString();
    }


}
