package com.lijuyong.startup.controller;

import com.lijuyong.startup.security.JwtUtil;
import com.lijuyong.startup.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class LoginController {
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
    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json;charset=utf8")
    public Map<String, String> login(@RequestParam(value = "username") String username, @RequestParam(value = "passwd") String passwd) {
        if (!LoginController.loginname.equals(username)) {
            throw new BadCredentialsException("invalid key");
        }
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        passwd
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
       // final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        User user = new User();
        user.setUsername(username);
        user.setId(123456789L);
        user.setRole("admin");
        // Perform the security
        final String token = jwtTokenUtil.generateToken(user);
        // Return the token
        // return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        HashMap<String, String> r = new HashMap<>();
        r.put("token", token);
        return r;
    }
    @RequestMapping("/open")
    public String open(){
        return "奇怪啦";
    }
}