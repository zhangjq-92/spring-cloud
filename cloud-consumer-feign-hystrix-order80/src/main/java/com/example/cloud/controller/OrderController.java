package com.example.cloud.controller;


import com.example.cloud.entity.Payment;
import com.example.cloud.service.OrderService;
import com.example.cloud.service.PaymentFeignService;
import com.example.cloud.vo.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
//@DefaultProperties(defaultFallback = "defaultFallBack")
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${server.port}")
    private String port;



    @HystrixCommand(fallbackMethod = "timeOutWithParam", commandProperties =
            {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
    @RequestMapping("/payment")
    public CommonResult<Payment> getOrderPayment(Long id){
        if (id < 0){
            id = id / 0;
        }
        CommonResult<Payment> result = orderService.getPayment();
        log.info(result.toString());
        return result;
    }


//    @HystrixCommand
    @RequestMapping("/payment/hystrix/ok")
    public CommonResult<Payment> getPaymentFeignHystrixOk(){
        CommonResult<Payment> result = paymentFeignService.getPaymentHystrixOk();
        log.info(result.toString());
        return result;
    }

//    @HystrixCommand(fallbackMethod = "timeOut", commandProperties =
//            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
    @RequestMapping("/payment/hystrix/error")
    public CommonResult<Payment> getPaymentFeignHystrixError(){
        CommonResult<Payment> result = paymentFeignService.getPaymentError();
        log.info(result.toString());
        return result;
    }

    @RequestMapping("/paymentfeign")
    public CommonResult<Payment> getPaymentFeign(){
        CommonResult<Payment> result = paymentFeignService.getPayment();
        log.info(result.toString());
        return result;
    }

    public CommonResult<Payment> timeOut(){
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("order 服务，支付服务正忙，或者订单服务报错，请稍后再试");
        commonResult.setData(null);
        log.info(commonResult.toString());

        return commonResult;
    }

    public CommonResult<Payment> timeOutWithParam(Long id){
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("order 服务，支付服务正忙，或者订单服务报错，请稍后再试 , id= " + id);
        commonResult.setData(null);
        log.info(commonResult.toString());

        return commonResult;
    }

    public CommonResult<Payment> defaultFallBack(){
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("order 服务默认熔断，支付服务正忙，或者订单服务报错，请稍后再试");
        commonResult.setData(null);
        log.info(commonResult.toString());

        return commonResult;
    }


}
