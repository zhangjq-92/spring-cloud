package com.example.cloud.controller;


import com.example.cloud.entity.Payment;
import com.example.cloud.service.OrderService;
import com.example.cloud.service.PaymentFeignService;
import com.example.cloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${server.port}")
    private String port;


    @RequestMapping("/payment")
    public CommonResult<Payment> getOrderPayment(){
        CommonResult<Payment> result = orderService.getPayment();
//        log.info(result.toString());
        return result;
    }

    @RequestMapping("/paymentfeign")
    public CommonResult<Payment> getPaymentFeign(){
        CommonResult<Payment> result = paymentFeignService.getPayment();
        log.info(result.toString());
        return result;
    }


}
