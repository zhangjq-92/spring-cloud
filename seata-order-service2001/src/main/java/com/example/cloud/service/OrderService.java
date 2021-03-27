package com.example.cloud.service;

import com.example.cloud.entity.Order;

public interface OrderService {

    int save(Order order);

    void update(Order order);

    Order findByid(Long id);

    void updateStatusById(Long orderId, Integer status);

    void createOrder(Order order);
}
