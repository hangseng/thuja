package com.thuja.mapper;

import com.thuja.model.ColumnInfo;
import com.thuja.model.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnInfoMappper {
    List<ColumnInfo> findByTableName(@Param("tableName")String tableName);

    Long findMaxByTableName(@Param("tableName")String tableName);

    List<TableInfo> findTablesByAttributes(@Param("pageIndex")Long pageIndex,@Param("pageSize")Long pageSize,@Param("tableName")String tableName);

    Long findTablesMaxByAttributes(@Param("tableName")String tableName);
}
