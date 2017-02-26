package com.lijuyong.startup.controller;

import com.lijuyong.startup.dao.mapper.SeqMapper;
import com.lijuyong.startup.dao.model.SequenceNoDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

/**
 * Created by john on 2017/2/26.
 */
@RestController
@RequestMapping("/seq")
public class SequenceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeqMapper seqMapper;
    @RequestMapping("/user")
    public Long userNo(@RequestParam("code") String code){
        SequenceNoDO sequenceNoDO = new SequenceNoDO();
        Long row = seqMapper.getUserNo(sequenceNoDO);
        logger.error("code is :" + code);
        return sequenceNoDO.getId();

    }
}
