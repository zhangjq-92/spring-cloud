package com.example.cloud.service.impl;

import com.example.cloud.entity.Payment;
import com.example.cloud.mapper.PaymentMapper;
import com.example.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public int savePayment(Payment payment) {
        return paymentMapper.savePayment(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
