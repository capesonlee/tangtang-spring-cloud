package com.lijuyong.startup.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/2/25.
 */

public class MyFilter extends ZuulFilter {
   // private Logger logger;
    public MyFilter(){
       // logger = LoggerFactory.getLogger(this.getClass());

    }

    @Override
    public String filterType() {
       // logger.error("pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
       // logger.error("999");
        return 999;
    }
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUri = ctx.getRequest().getRequestURI();
       // logger.error("这是啥");
       // logger.error(requestUri);
        System.out.println(requestUri);

        return requestUri.startsWith("/user-service");

    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("payload.trace", "true");
        return null;
    }
}
