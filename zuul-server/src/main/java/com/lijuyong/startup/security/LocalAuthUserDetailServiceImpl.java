package com.lijuyong.startup.security;

import com.lijuyong.startup.controller.LoginController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/3/8.
 */
@Service
public class LocalAuthUserDetailServiceImpl implements UserDetailsService {
    //    @Autowired
//    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     User user = userRepository.findByUsername(username);
        //判断一个用户是否存在,此处 还未从数据库查询, 简单测试

        if (!LoginController.loginname.equals(username)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new LocalAuthUser(username);
//            return JwtUserFactory.create(user);
        }
    }
}