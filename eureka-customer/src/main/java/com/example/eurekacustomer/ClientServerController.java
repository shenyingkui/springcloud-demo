package com.example.eurekacustomer;

import com.example.fegin.HouseRemoteClient;
import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.hystrix.FallbackHandler;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.Map;

@RestController
public class ClientServerController {
    @Autowired
    private HouseRemoteClient houseRemoteClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/callHello3")
    public String callHello(){
        return restTemplate.getForObject("http://eureka-service/house/hello",String.class);
    }

    @GetMapping("/callHello4")
    public String callHello4(){
        System.out.println("callhello4");
        return houseRemoteClient.hello();
    }

    public String defaultCallHello(){
        return "fail";
    }

     static class DefaultCallHello implements FallbackHandler {

         @Override
         public Observable getFallback(HystrixInvokableInfo hystrixInfo, Map requestProperties) {
             System.out.println("回退");
             return null;
         }
     }
}
