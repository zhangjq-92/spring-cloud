package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@RibbonClient(name = "payment-service", configuration = CustomerBalanceRule.class)
//@RibbonClient(name = "PAYMENT-SERVICE", configuration = CustomerBalanceRule.class)
public class SentinelOrderApplication84 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelOrderApplication84.class, args);
    }
}
