package com.lijuyong.startup.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final JwtTokenAuthenticationService tokenAuthenticationService;
    private final UserDetailsService userDetailsService;

    protected JwtLoginFilter(String urlMapping,
                             JwtTokenAuthenticationService tokenAuthenticationService,
                             UserDetailsService userDetailsService,
                             AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(urlMapping));
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userDetailsService = userDetailsService;

    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authentication)
            throws IOException,ServletException {

        


    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                               HttpServletResponse response)
            throws AuthenticationException,IOException,ServletException {
        final User user = new ObjectMapper().readValue(request.getInputStream(),User.class);
        final UsernamePasswordAuthenticationToken loginToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getId());
        return getAuthenticationManager().authenticate(loginToken);

    }
}
