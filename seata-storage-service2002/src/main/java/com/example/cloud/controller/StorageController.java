package com.example.cloud.controller;


import com.example.cloud.entity.Order;
import com.example.cloud.entity.Storage;
import com.example.cloud.service.StorageService;
import com.example.cloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Resource(name = "storageService")
    private StorageService storageService;


    @GetMapping("/create")
    public CommonResult<Order> createOrder(Storage storage){
        CommonResult<Order> result = new CommonResult<>();


        return result;
    }

    @PostMapping("/decrease")
    public CommonResult decrease(@RequestParam("produceId") Long produceId, @RequestParam("count") Integer count){
        CommonResult<Order> result = new CommonResult<>();
        log.info("减库存");
        try {
            storageService.decrease(produceId, count);
            result.setMessage("库存服务减库存成功");
            result.setCode("200");
        } catch (Exception e) {
            result.setMessage("库存服务减库存失败");
            result.setCode("500");
            log.error("减库存失败", e);
        }

        return result;
    }
}
