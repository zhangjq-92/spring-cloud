package com.example.cloud.controller;


import com.example.cloud.entity.Order;
import com.example.cloud.service.OrderService;
import com.example.cloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;


    @GetMapping("/create")
    public CommonResult<Order> createOrder(Order order){
        CommonResult<Order> result = new CommonResult<>();
        result.setData(order);

        try {
            orderService.createOrder(order);
            result.setCode("200");
            result.setMessage("创建订单成功");
        } catch (Exception e) {
            result.setCode("500");
            result.setMessage("创建订单失败");
            log.error("创建订单失败", e);
        }

        return result;
    }
}
