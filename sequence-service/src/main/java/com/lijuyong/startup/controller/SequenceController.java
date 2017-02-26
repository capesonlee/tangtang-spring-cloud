package com.lijuyong.startup.controller;

import com.lijuyong.startup.dao.mapper.SeqMapper;
import com.lijuyong.startup.dao.model.SequenceNoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john on 2017/2/26.
 */
@RestController
@RequestMapping("/seq")
public class SequenceController {
    @Autowired
    private SeqMapper seqMapper;
    @RequestMapping("/user")
    public int userNo(){
        SequenceNoDO sequenceNoDO = new SequenceNoDO();
        Integer row = seqMapper.getUserNo(sequenceNoDO);
        return sequenceNoDO.getId();

    }
}
