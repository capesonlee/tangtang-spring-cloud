package com.lijuyong.startup.controller;

import com.lijuyong.startup.auth.model.JwtUser;
import com.lijuyong.startup.auth.jwt.JwtUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/3/8.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Log logger = LogFactory.getLog(this.getClass());
    private String tokenHeader = "Authorization";
    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    public static final String loginname = "abcefg";
    public static final String loginpwd_raw = "111";
    public static final String loginpwd = "$2a$10$qsYvMwvld7FMGKp45AQjpun6otC8b.eFN7Be5KAr0vuEQWgT.uvgm";

    @RequestMapping("/open")
    public String open(){

        return "这是一个开放测试接口";
    }
}