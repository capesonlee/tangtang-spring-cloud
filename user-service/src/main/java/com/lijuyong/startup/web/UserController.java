package com.lijuyong.startup.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by john on 2017/2/24.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/hello")
    public String getUserHello(HttpServletRequest request){
        System.out.println(request.getHeader("x-youbang-user"));
        System.out.println(request.getHeader("Authorization"));
        return  "这是一个伟大的开端";
    }
}
