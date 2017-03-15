package com.lijuyong.startup.auth;

import com.lijuyong.startup.auth.filter.JwtAuthenticationFilter;
import com.lijuyong.startup.auth.filter.JwtLoginFilter;
import com.lijuyong.startup.auth.security.JwtAuthenticationSuccessHandler;
import com.lijuyong.startup.auth.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by john on 2017/3/8.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        //$2a$10$qsYvMwvld7FMGKp45AQjpun6otC8b.eFN7Be5KAr0vuEQWgT.uvgm
        //对应的密码是111
//        return new BCryptPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }
    @Bean
    public JwtLoginFilter jwtLoginFilterBean() throws Exception {
        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter("/auth/signin",
                authenticationManager());
        jwtLoginFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
        return jwtLoginFilter;
    }
    @Override
    public void configure(WebSecurity webSecurity) {

    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/open").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                //.antMatchers(HttpMethod.POST, "/auth/signin").permitAll()
                .anyRequest().authenticated();


        //custom login for jwt token generation
        httpSecurity.addFilterBefore(jwtLoginFilterBean(),
                UsernamePasswordAuthenticationFilter.class);

        // Custom JWT based auth filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(),
                        UsernamePasswordAuthenticationFilter.class);
        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}