package com.example.healthhandlerprovider;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    static Boolean canVisitDB = false;

    @RequestMapping(value = "/db/{canVisitDB}")
    public String setConnectState(@PathVariable("canVisitDB") Boolean canVisitDB){
        this.canVisitDB = canVisitDB;
        return "数据库连接是否正常:"+this.canVisitDB;
    }
}
