package com.example.cloud.config.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {

    ServiceInstance getService(List<ServiceInstance> serviceInstances);
}
