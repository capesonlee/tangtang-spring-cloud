package com.lijuyong.startup.auth.filter;

import com.lijuyong.startup.auth.jwt.JwtTokenAuthenticationService;
import com.lijuyong.startup.auth.model.JwtUser;
import com.lijuyong.startup.auth.jwt.JwtUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by john on 2017/3/6.
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Log logger = LogFactory.getLog(this.getClass());
//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private JwtTokenAuthenticationService jwtTokenAuthenticationService;
    //    @Value("${jwt.header}")
    private String tokenHeader = "Authorization";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        String authToken = jwtTokenAuthenticationService.parseTokenHeader(request);
        if( authToken == null){
            chain.doFilter(request, response);
        }

        JwtUser user = jwtTokenUtil.parseToken(authToken);
        logger.info("checking authentication for user " + user.getName());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getId(),user.getRoleId(),null);
        //这里只需要一个authenticated字段，其他的字段暂时不需要。
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);


    }
}