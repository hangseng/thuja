package com.thuja.modules.system.mapper;

import com.thuja.modules.system.domain.User;
import com.thuja.modules.system.service.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author WangHengSheng
 * @date 2022/1/20 15:40
 */
@Mapper
public interface UserMapper {
    UserDto findByUsername(@Param("userName")String userName);
}
