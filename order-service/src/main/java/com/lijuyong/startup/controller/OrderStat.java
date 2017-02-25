package com.lijuyong.startup.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john on 2017/2/24.
 */
@RestController
@RequestMapping("/stat")
public class OrderStat {
    @Value("${from}")
    private  String from;
    @RequestMapping("/count")
    public int count(){
        return 2046;
    }

    @RequestMapping("/config")
    public String config(){
        return  from;
    }
}
