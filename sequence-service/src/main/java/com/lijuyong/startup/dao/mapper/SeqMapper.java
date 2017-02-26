package com.lijuyong.startup.dao.mapper;

import com.lijuyong.startup.dao.model.SequenceNoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by john on 2017/2/26.
 */
@Mapper
public interface SeqMapper {
    Long getUserNo(@Param("sequenceNoDO")SequenceNoDO sequenceNoDO);
}
