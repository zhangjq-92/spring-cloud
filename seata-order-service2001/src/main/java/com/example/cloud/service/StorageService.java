package com.example.cloud.service;

import com.example.cloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    @PostMapping(value = "/seata-storage/storage/decrease")
    CommonResult decrease(@RequestParam("produceId") Long produceId, @RequestParam("count") Integer count);
}
