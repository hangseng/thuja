package com.thuja.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: Ansen
 * @create: 2022-01-10 23:16
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenConfig {
    public GenConfig(String tableName) {
        this.tableName = tableName;
    }

    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    
    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "接口名称")
    private String apiAlias;


    @ApiModelProperty(value = "包路径")
    private String pack;


    @ApiModelProperty(value = "模块名")
    private String moduleName;

    
    @ApiModelProperty(value = "前端文件路径")
    private String path;

    @ApiModelProperty(value = "前端文件路径")
    private String apiPath;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "表前缀")
    private String prefix;

    @ApiModelProperty(value = "是否覆盖")
    private Boolean cover = false;
}
