package com.thuja.service;

import com.thuja.model.ColumnInfo;
import com.thuja.model.GenConfig;
import com.thuja.model.TableInfo;

import java.util.List;

public interface GeneratorService {
    /**
     * 代码生成
     * @param genConfig 配置信息
     * @param columns 字段信息
     */
    void generator(GenConfig genConfig, List<ColumnInfo> columns);


    List<ColumnInfo> getColumns(String name);

    List<TableInfo> findTablesByAttributes(Long pageIndex,Long pageSize,String name);
}
