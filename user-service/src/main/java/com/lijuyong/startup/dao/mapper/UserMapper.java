package com.lijuyong.startup.dao.mapper;

import com.lijuyong.startup.dao.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by john on 2017/3/13.
 */
@Mapper
public interface UserMapper {
    UserDO getUserById(Long id);
}
