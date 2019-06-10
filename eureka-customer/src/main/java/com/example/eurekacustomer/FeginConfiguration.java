package com.example.eurekacustomer;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfiguration {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
  //fegin 超时配置
    // Options 第一个参数是连接超时时间（ms），默认10* 1000
    // 第二个取值超时时间（ms）默认是60 * 1000
    @Bean
    public Request.Options options(){
        return new Request.Options(5000,10000);
    }

    //加上这个配置之后，springmvc 的注解就不能是使用了。只能使用
    //原生的fegin 注解
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }
    //认证方式
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password");
    }
}
