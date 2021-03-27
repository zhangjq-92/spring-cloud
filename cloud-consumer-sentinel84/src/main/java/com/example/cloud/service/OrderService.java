package com.example.cloud.service;

import com.example.cloud.entity.Payment;
import com.example.cloud.vo.CommonResult;

public interface OrderService {

    CommonResult<Payment> getPayment();

    /**
     * 自定义负载均衡算法
     * @return
     */
    CommonResult<Payment> getPaymentBySelfLB();
}
