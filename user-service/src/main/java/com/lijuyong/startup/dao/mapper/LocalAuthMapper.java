package com.lijuyong.startup.dao.mapper;

import com.lijuyong.startup.dao.entity.LocalAuthDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by john on 2017/3/13.
 */
@Mapper
public interface LocalAuthMapper {
    LocalAuthDO getLocalAuthByUserId(Long userId);
}
