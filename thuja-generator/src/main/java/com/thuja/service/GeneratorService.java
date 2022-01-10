package com.thuja.service;

import com.thuja.model.ColumnInfo;
import com.thuja.model.GenConfig;

import java.util.List;

public interface GeneratorService {
    /**
     * 代码生成
     * @param genConfig 配置信息
     * @param columns 字段信息
     */
    void generator(GenConfig genConfig, List<ColumnInfo> columns);
}