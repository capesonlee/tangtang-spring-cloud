package com.lijuyong.startup.manager.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by john on 2017/3/16.
 */
@FeignClient("sequence-service")
public interface SequenceClient {
    @RequestMapping("/seq/user")
    public Long applyUserId(@RequestParam("code") String code);
}
