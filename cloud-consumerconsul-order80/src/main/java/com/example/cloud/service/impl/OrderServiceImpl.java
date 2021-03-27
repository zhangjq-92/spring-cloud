package com.example.cloud.service.impl;

import com.example.cloud.entity.Payment;
import com.example.cloud.service.OrderService;
import com.example.cloud.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

//    private static final String PAYMENT_URL = "http://consul-order-service/payment-service/payment/test";
    private static final String PAYMENT_URL = "http://payment-service/payment-service/payment/test";
//
//    private static final String PAYMENT_URL = "http://127.0.0.1:8003/payment-service/payment/test";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public CommonResult<Payment> getPayment() {
        CommonResult<Payment> commonResult = null;
        try {
            ResponseEntity<CommonResult> result = restTemplate.getForEntity(PAYMENT_URL, CommonResult.class);
            commonResult = result.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return commonResult;
    }
}
