package com.example.cloud.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private UserRealm userRealm;

    //
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultSecurityManager") DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultSecurityManager);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/user/add", "authc");
        map.put("/user/update", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        factoryBean.setLoginUrl("/user/tologin");
        return factoryBean;
    }


    //创建securityManager对象
    @Bean
    public DefaultSecurityManager defaultSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建realm对象
    /*@Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }*/

    //shiro和thymeleaf的整合
    @Bean
    public ShiroDialect shiroDialect(){
       return new ShiroDialect();
    }
}
