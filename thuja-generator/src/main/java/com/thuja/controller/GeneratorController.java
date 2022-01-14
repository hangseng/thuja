package com.thuja.controller;

import cn.hutool.core.util.PageUtil;
import com.thuja.error.ReturnValue;
import com.thuja.model.TableInfo;
import com.thuja.service.GenConfigService;
import com.thuja.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Ansen
 * @create: 2022-01-10 22:57
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generator")
@Api(tags = "系统：代码生成管理")
public class GeneratorController {
    private final GeneratorService generatorService;
    private final GenConfigService genConfigService;




    @ApiOperation("查询数据库数据")
    @GetMapping(value = "/tables")
    public ReturnValue<List<TableInfo>> findTablesByAttributes(@RequestParam(name="pageIndex",required=true) Long pageIndex,
                                                         @RequestParam(name="pageSize",required=true) Long pageSize,
                                                         @RequestParam(name="name",required=false) String name){
        List<TableInfo> list= generatorService.findTablesByAttributes(pageIndex,pageSize,name);

        return new ReturnValue<>(list);
    }

    @ApiOperation("生成代码")
    @PostMapping(value = "/{tableName}/{type}")
    public ReturnValue<String> generatorCode(@PathVariable String tableName, @PathVariable Integer type, HttpServletRequest request, HttpServletResponse response){
        switch (type){
            // 生成代码
            case 0: generatorService.generator(genConfigService.find(tableName), generatorService.getColumns(tableName));
                break;
            // 预览
//            case 1: return generatorService.preview(genConfigService.find(tableName), generatorService.getColumns(tableName));
            // 打包
//            case 2: generatorService.download(genConfigService.find(tableName), generatorService.getColumns(tableName), request, response);
//                break;
            default: throw new RuntimeException("没有这个选项");
        }
        return new ReturnValue<>("");
    }
}
