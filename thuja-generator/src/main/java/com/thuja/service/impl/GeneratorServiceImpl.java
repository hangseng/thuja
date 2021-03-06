package com.thuja.service.impl;

import com.thuja.mapper.ColumnInfoMappper;
import com.thuja.model.ColumnInfo;
import com.thuja.model.GenConfig;
import com.thuja.model.TableInfo;
import com.thuja.service.GeneratorService;
import com.thuja.util.GenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author: Ansen
 * @create: 2022-01-10 23:18
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {

    private final ColumnInfoMappper columnInfoMappper;

    @Override
    public void generator(GenConfig genConfig, List<ColumnInfo> columns) {
        if (genConfig.getId() == null) {
            throw new RuntimeException("请先配置生成器");
        }
        try {
            GenUtil.generatorCode(columns, genConfig);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("生成失败，请手动处理已生成的文件");
        }
    }

    @Override
    public List<ColumnInfo> getColumns(String name) {
        return columnInfoMappper.findByTableName(name);
    }

    @Override
    public List<TableInfo> findTablesByAttributes(Long pageIndex, Long pageSize, String name) {
        return columnInfoMappper.findTablesByAttributes(pageIndex,pageSize,name);
    }


}
