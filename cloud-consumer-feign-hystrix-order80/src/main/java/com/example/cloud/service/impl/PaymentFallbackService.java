package com.example.cloud.service.impl;

import com.example.cloud.entity.Payment;
import com.example.cloud.service.PaymentFeignService;
import com.example.cloud.vo.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentFeignService {
    public CommonResult<Payment> getPayment() {
        return null;
    }

    public CommonResult<Payment> getPaymentError() {
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("getPaymentError请求成功，请求的服务忙，请稍后再试");
        commonResult.setData(null);
        return commonResult;
    }

    public CommonResult<Payment> getPaymentHystrixOk() {
        CommonResult<Payment> commonResult = new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("getPaymentHystrixOk请求成功，请求的服务忙，请稍后再试");
        commonResult.setData(null);
        return commonResult;
    }
}
