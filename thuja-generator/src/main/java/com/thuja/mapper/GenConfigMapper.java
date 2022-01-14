package com.thuja.mapper;

import com.thuja.model.GenConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author WangHengSheng
 * @date 2022/1/14 9:12
 */
@Mapper
public interface GenConfigMapper {
    GenConfig findByTableName(@Param("tableName")String tableName);
}
