package com.thuja.mapper;

import com.thuja.model.ColumnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnInfoMappper {
    List<ColumnInfo> findByTableName(@Param("tableName")String tableName);
}
