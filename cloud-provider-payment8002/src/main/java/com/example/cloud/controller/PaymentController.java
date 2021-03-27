package com.example.cloud.controller;

import com.example.cloud.entity.Payment;
import com.example.cloud.service.PaymentService;
import com.example.cloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource(name = "paymentService")
    private PaymentService paymentService;

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/test")
    public CommonResult<Payment> getPayment(){
        Payment paymentById = paymentService.getPaymentById(1L);
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("请求成功，服务名： " + serverName + "服务端口： " + port);
        commonResult.setData(paymentById);
        log.info(commonResult.toString());
        return commonResult;
    }
}
