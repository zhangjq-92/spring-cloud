package com.example.cloud.service;

import com.example.cloud.entity.Payment;

public interface PaymentService {

    int savePayment(Payment payment);

    Payment getPaymentById(Long id);
}
