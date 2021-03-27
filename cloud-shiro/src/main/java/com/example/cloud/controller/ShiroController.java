package com.example.cloud.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class ShiroController {

    @GetMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("msg", "helloShiro");
        return "index";
    }

    @GetMapping("/user/add")
    public String userAdd(){
        return "user/add";
    }

    @GetMapping("/user/update")
    public String userUpdate(){
        return "user/update";
    }

    @GetMapping("/user/tologin")
    public String tologin(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userName, String password, Map map){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            return "/index";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.put("msg", "登录失败");
            return "user/login";
        }
    }

}
