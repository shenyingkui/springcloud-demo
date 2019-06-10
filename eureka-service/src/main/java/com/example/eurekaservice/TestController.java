package com.example.eurekaservice;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class TestController {

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

   /* @Autowired
    private DiscoveryClient discoveryClient;
*/
    @GetMapping("/hello")
    public String hello(){
        System.out.println("====");
        return "hello";
    }

    @GetMapping("/infos")
    public Object serviceUrl(){
        return eurekaClient.getInstancesByVipAddress("eureka-service",false);
    }
/*
    @GetMapping("infos2")
    public Object serviceUrl2(){
        return discoveryClient.getInstancesById("eureka-service");
    }*/
}
