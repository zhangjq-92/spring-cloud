package com.example.cloud.controller;

import com.example.cloud.entity.Permission;
import com.example.cloud.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/page")
@RestController
public class TestPageController {

    @Resource(name = "permissionService")
    private PermissionService permissionService;

    @PostMapping("/test")
    public PageInfo<Permission> testPage(int currentPage, int pageSize){
        return permissionService.findByPage(currentPage, pageSize);
    }

    @GetMapping("/server")
    public String server(){
        return "alibaba";
    }
}
