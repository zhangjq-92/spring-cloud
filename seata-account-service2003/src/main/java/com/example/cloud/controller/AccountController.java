package com.example.cloud.controller;


import com.example.cloud.entity.Account;
import com.example.cloud.service.AccountService;
import com.example.cloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Resource(name = "accountService")
    private AccountService accountService;

    @PostMapping(value = "/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        CommonResult<Account> result = new CommonResult<>();
        log.info("账户服务更新账户信息");
        try {
            accountService.decrease(userId, money);
        } catch (Exception e) {
            log.error("账户服务更新账户信息失败");
            throw e;
        }
        return result;
    }
}
