package com.example.cloud.service;

import com.example.cloud.entity.Payment;
import com.example.cloud.vo.CommonResult;

public interface OrderService {

    CommonResult<Payment> getPayment();

}
