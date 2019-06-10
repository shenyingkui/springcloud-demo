package com.example.fegin;

import com.example.eurekacustomer.FeginConfiguration;
import com.example.eurekacustomer.HouseRemoteClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-service",path = "/house",
        configuration = FeginConfiguration.class,
        fallback = HouseRemoteClientHystrix.class)
public interface HouseRemoteClient {
    @GetMapping("/hello2")
    String hello();
}
