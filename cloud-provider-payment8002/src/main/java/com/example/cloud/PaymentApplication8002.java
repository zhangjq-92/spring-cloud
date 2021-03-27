package com.example.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan(basePackages = "com.example.cloud.mapper")
@EntityScan(basePackages = "com.example.cloud.entity")
@SpringBootApplication
@EnableEurekaClient
public class PaymentApplication8002 {


    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8002.class, args);
    }
}
