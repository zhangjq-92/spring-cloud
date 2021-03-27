package com.example.ribbonbalance;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CustomerBalanceRule {

    @Bean
    public IRule customerRule(){
        return new RandomRule();                    //随机
//        return new RoundRobinRule();              //轮询
//        return new RetryRule();                   //随机
//        return new WeightedResponseTimeRule();    //对RoundRobinRule的扩展，相应越快的实例越容易被选
//        return new BestAvailableRule();           //先过滤掉由于多次访问故障而处于断路器跳闸的服务，然后选择一个并发量较小的服务
//        return new AvailabilityFilteringRule();   //过滤掉故障实例，选择并发量较小的实例
//        return new ZoneAvoidanceRule();           //默认规则复合判断server所在区域的性能和server的可用性选择服务器
    }
}
