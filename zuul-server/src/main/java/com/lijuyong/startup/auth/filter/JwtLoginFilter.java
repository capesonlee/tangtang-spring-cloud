package com.lijuyong.startup.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijuyong.startup.auth.jwt.JwtTokenAuthenticationService;
import com.lijuyong.startup.auth.jwt.JwtUtil;
import com.lijuyong.startup.auth.model.JwtUser;
import com.lijuyong.startup.auth.model.LocalAuthUser;
import com.lijuyong.startup.auth.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by john on 2017/3/9.
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtTokenAuthenticationService jwtTokenAuthenticationService;


    public JwtLoginFilter(String urlMapping, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        final LoginUser user = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getUser(), user.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {


        LocalAuthUser localAuthUser = (LocalAuthUser)authentication.getPrincipal();


        //下面的数据应该从user-service获取，入参：userId,出参：jwt User.
        JwtUser jwtUser = new JwtUser();
        jwtUser.setName(localAuthUser.getUsername());
        jwtUser.setId(localAuthUser.getUserId());
        jwtUser.setRoleId(9L);
        jwtUser.setOrgId(123L);

        String token = jwtUtil.generateToken(jwtUser);
        jwtTokenAuthenticationService.addAuthorizationHeader(response,token);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
