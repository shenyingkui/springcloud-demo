package com.example.eurekacustomer;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SubstitutionController2 {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/callHello2")
    public String callHello(){
        return restTemplate.getForObject("http://eureka-service/house/hello",String.class);
    }
}
