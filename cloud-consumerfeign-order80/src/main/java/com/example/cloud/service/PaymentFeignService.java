package com.example.cloud.service;

import com.example.cloud.entity.Payment;
import com.example.cloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "payment-service")
public interface PaymentFeignService {

    @RequestMapping("/payment-service/payment/test")
    CommonResult<Payment> getPayment();

}
