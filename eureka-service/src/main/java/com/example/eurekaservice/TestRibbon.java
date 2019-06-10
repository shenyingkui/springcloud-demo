package com.example.eurekaservice;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestRibbon {
    public static void main(String [] args){
        List<Server> serverList = new ArrayList<>();
        serverList.add(new Server("www.baidu.com",80));
        serverList.add(new Server("www.csdn.net",80));

        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);

        for(int i=0;i<5;i++){
            String result = LoadBalancerCommand.<String>builder()
                    .withLoadBalancer(loadBalancer)
                    .build()
                    .submit(new ServerOperation<String>() {
                        @Override
                        public Observable<String> call(Server server) {
                            try{
                                String addr ="http://"+server.getHost()+":"+server.getPort()+"/house/hello";
                                System.out.println("调用地址:"+addr);
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream in = conn.getInputStream();
                                return Observable.just(new String(""));
                            }catch (Exception e){
                                e.printStackTrace();
                                return Observable.error(e);
                            }
                        }
                    }).toBlocking().first();

            System.out.println("调用结果:"+result);
        }

















    }
}
