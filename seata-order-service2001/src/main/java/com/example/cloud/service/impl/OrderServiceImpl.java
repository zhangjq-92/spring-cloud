package com.example.cloud.service.impl;

import com.example.cloud.entity.Order;
import com.example.cloud.mapper.OrderMapper;
import com.example.cloud.service.AccountService;
import com.example.cloud.service.OrderService;
import com.example.cloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private AccountService accountService;

    @Autowired
    private OrderMapper orderMapper;

    @Resource
    private StorageService storageService;

    @Override
    public int save(Order order) {
        int insert = orderMapper.insert(order);
        return insert;
    }

    @Override
    public void update(Order order) {
        orderMapper.updateById(order);
    }

    @Override
    public Order findByid(Long id) {

        Order order = orderMapper.selectById(id);
        return order;
    }

    @Override
    public void updateStatusById(Long orderId, Integer status) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);
        update(order);
    }

    @Override
    @GlobalTransactional(name = "fsp-order-service", rollbackFor = Exception.class)
    public void createOrder(Order order) {
        log.info("开始创建订单");
        save(order);
        log.info("订单服务--订单创建成功");

        log.info("订单服务--开始调用库存服务做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("库存服务--扣减结束，成功");


        log.info("订单服务--开始调用账户服务做扣减");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("账户服务--账户扣钱结束，成功");

        log.info("订单服务--更新订单状态");
        updateStatusById(order.getId(), 1);
        log.info("订单服务--更新订单状态成功");
        log.info("订单服务--订单交易成功");

    }
}