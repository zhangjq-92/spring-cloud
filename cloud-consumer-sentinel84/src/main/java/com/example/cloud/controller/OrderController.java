package com.example.cloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.cloud.entity.Payment;
import com.example.cloud.service.OrderService;
import com.example.cloud.service.PaymentFeignService;
import com.example.cloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${server.port}")
    private String port;

    @Resource
    private PaymentFeignService paymentFeignService;


    @RequestMapping("/payment")
    @SentinelResource(value = "getOrderPayment", blockHandler = "blockMethod", fallback = "orderFallBack",
    exceptionsToIgnore = {ArithmeticException.class})
//    @SentinelResource(value = "getOrderPayment", fallback = "orderFallBack")
//    @SentinelResource(value = "getOrderPayment")
    public CommonResult<Payment> getOrderPayment(String id){
        CommonResult<Payment> result = null;
        try {
            if (StringUtils.isNotBlank(id)){
                int i = Integer.parseInt(id) / 0;
            }
            result = orderService.getPayment();
//        CommonResult<Payment> result = orderService.getPaymentBySelfLB();
            log.info(result.toString());
        } catch (Exception e) {
            log.error("order controller error", e);
            throw e;
        }
        return result;
    }

    @RequestMapping("/getById")
    @SentinelResource(value = "getById", blockHandler = "blockMethod", fallback = "orderFallBack",
            exceptionsToIgnore = {ArithmeticException.class})
    @PostMapping("/getById")
    public CommonResult<Payment> getById(String id){
        CommonResult<Payment> result = paymentFeignService.getPaymentById(id);
        return result;
    }

    public CommonResult<Payment> orderFallBack(String id){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("order 异常，暂时不能访问");
        commonResult.setData(null);
        return commonResult;
    }

    public CommonResult<Payment> blockMethod(String id, BlockException blockException){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("order 限流，请稍后访问");
        commonResult.setData(null);
        return commonResult;
    }


}
