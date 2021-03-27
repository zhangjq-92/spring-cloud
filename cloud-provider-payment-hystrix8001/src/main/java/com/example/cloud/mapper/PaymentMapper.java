package com.example.cloud.mapper;

import com.example.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {

    int savePayment(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
