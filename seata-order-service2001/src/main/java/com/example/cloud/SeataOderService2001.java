package com.example.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.example.cloud.mapper")
@EntityScan(basePackages = "com.example.cloud.entity")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOderService2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOderService2001.class, args);
    }
}
