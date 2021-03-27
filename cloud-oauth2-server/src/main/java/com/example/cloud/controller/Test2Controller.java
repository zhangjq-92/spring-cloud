package com.example.cloud.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
public class Test2Controller {

    @GetMapping("/test")
    public String test(){
        return "test2";
    }
}
