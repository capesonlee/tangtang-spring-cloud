package com.lijuyong.startup.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john on 2017/3/8.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Log logger = LogFactory.getLog(this.getClass());
    public static final String loginname = "abcefg";
    public static final String loginpwd_raw = "111";
    public static final String loginpwd = "$2a$10$qsYvMwvld7FMGKp45AQjpun6otC8b.eFN7Be5KAr0vuEQWgT.uvgm";

    @RequestMapping("/open")
    public String open(){

        logger.info("here we have open interface");
        return "这是一个开放测试接口";
    }
}