package com.thuja.modules.system.controller;

import com.thuja.error.ReturnValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangHengSheng
 * @date 2022/1/21 15:40
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {


    @GetMapping
    @PreAuthorize("hasAnyRole('roles:list')")
    @ApiOperation("获取所有用户的信息")
    public ReturnValue<Object> getAllUser() {
        return new ReturnValue();
    }
}
