package com.example.healthhandlerprovider;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthCheckHandler implements HealthCheckHandler {
    @Autowired
    private MyHealthIndicator indicator;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status s = indicator.health().getStatus();
        if(s.equals(Status.DOWN)){
            System.out.println("数据库连接失败");
            return InstanceInfo.InstanceStatus.DOWN;
        }else{
            System.out.println("数据库连接正常");
            return InstanceInfo.InstanceStatus.UP;
        }
    }
}
