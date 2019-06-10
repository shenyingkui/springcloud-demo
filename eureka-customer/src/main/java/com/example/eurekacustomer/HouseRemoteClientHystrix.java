package com.example.eurekacustomer;

import com.example.fegin.HouseRemoteClient;
import org.springframework.stereotype.Component;

@Component
public class HouseRemoteClientHystrix implements HouseRemoteClient {

    @Override
    public String hello() {
        return "HYSTRIX";
    }
}
