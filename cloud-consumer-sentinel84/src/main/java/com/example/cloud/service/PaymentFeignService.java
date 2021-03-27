package com.example.cloud.service;

import com.example.cloud.entity.Payment;
import com.example.cloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service("paymentFeignService")
//@Component
@FeignClient(value = "payment-service-sentinel")
public interface PaymentFeignService {


    @PostMapping("/payment-service/payment/getById")
    CommonResult<Payment> getPaymentById(String id);
}
