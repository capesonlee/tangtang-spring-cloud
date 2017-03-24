package com.lijuyong.startup.auth.security;

import com.lijuyong.startup.web.AuthController;
import com.lijuyong.startup.auth.model.LocalAuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/3/8.
 */
@Service
public class LocalAuthUserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //调用user-service的接口获取用户的认证信息
        if (!AuthController.loginname.equals(username)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.",
                    username));
        } else {
            //差一个user id
            return new LocalAuthUser(username,AuthController.loginpwd,20110211L);
        }
    }
}