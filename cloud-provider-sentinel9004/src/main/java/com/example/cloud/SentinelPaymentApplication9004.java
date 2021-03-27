package com.example.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = "com.example.cloud.mapper")
@EntityScan(basePackages = "com.example.cloud.entity")
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelPaymentApplication9004 {


    public static void main(String[] args) {
        SpringApplication.run(SentinelPaymentApplication9004.class, args);
    }
}
