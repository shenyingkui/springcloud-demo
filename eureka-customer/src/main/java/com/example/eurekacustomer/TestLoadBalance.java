package com.example.eurekacustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLoadBalance {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/choose")
    public Object chooseUrl(){
        ServiceInstance instance  = loadBalancerClient.choose("eureka-service");
        return instance;
    }
}
