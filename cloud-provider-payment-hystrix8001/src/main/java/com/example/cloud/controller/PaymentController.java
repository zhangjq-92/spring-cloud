package com.example.cloud.controller;

import com.example.cloud.entity.Payment;
import com.example.cloud.service.PaymentService;
import com.example.cloud.vo.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
        /*try {
            TimeUnit.SECONDS.sleep(2L);
            log.info("8001 sleep 2m");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Payment paymentById = paymentService.getPaymentById(1L);
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("请求成功，服务名： " + serverName + "服务端口： " + port);
        commonResult.setData(paymentById);
        log.info(commonResult.toString());

        return commonResult;
    }

    @RequestMapping("/hystrix/ok")
    public CommonResult<Payment> getPaymentHystrixOk(){
        /*try {
            TimeUnit.SECONDS.sleep(2L);
            log.info("8001 sleep 2m");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Payment paymentById = paymentService.getPaymentById(1L);
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("请求成功，服务名： " + serverName + "服务端口： " + port);
        commonResult.setData(paymentById);
        log.info(commonResult.toString());

        return commonResult;
    }

    @HystrixCommand(fallbackMethod = "timeOut", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @RequestMapping("/hystrix/error")
    public CommonResult<Payment> getPaymentHystrixError(){
        try {
            TimeUnit.SECONDS.sleep(2L);
            log.info("8001 sleep 2m");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Payment paymentById = paymentService.getPaymentById(1L);
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("请求成功，服务名： " + serverName + "服务端口： " + port);
        commonResult.setData(paymentById);
        log.info(commonResult.toString());

        return commonResult;
    }

    public CommonResult<Payment> timeOut(){
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("请求成功，服务名： " + serverName + "服务端口： " + port + "请求的服务忙，请稍后再试");
        commonResult.setData(null);
        log.info(commonResult.toString());

        return commonResult;
    }
}
