package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@RibbonClient(name = "payment-service", configuration = CustomerBalanceRule.class)
//@RibbonClient(name = "PAYMENT-SERVICE", configuration = CustomerBalanceRule.class)
public class OrderApplicationNacos8083 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationNacos8083.class, args);
    }
}
