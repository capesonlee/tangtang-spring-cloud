package com.lijuyong.startup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john on 2017/2/24.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/hello")
    public String getUserHello(){
        return  "这是一个伟大的开端";
    }
}
