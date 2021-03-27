package com.example.cloud.service;

import com.example.cloud.entity.Payment;
import com.example.cloud.service.impl.PaymentFallbackService;
import com.example.cloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "payment-service", fallback = PaymentFallbackService.class)
public interface PaymentFeignService {

    @RequestMapping("/payment-service/payment/test")
    CommonResult<Payment> getPayment();

    @RequestMapping("/payment-service/payment/hystrix/error")
    CommonResult<Payment> getPaymentError();

    @RequestMapping("/payment-service/payment/hystrix/ok")
    CommonResult<Payment> getPaymentHystrixOk();

}
