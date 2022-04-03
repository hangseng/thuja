package com.thuja.modules.system.controller;

import com.thuja.annotation.Log;
import com.thuja.error.ReturnValue;
import com.thuja.modules.system.mapper.UserMapper;
import com.thuja.modules.system.service.UserService;
import com.thuja.modules.system.service.dto.UserDto;
import com.thuja.modules.system.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("getAllUser")
    //@PreAuthorize("@el.check('admin')")
    @ApiOperation("获取所有用户的信息")
    public ReturnValue<Object> getAllUser() {
        return new ReturnValue();
    }

    @Log("测试测试")
    @GetMapping("gerUserById")
    public UserDto getUserById(String name) {
        return  userService.findByName(name);
    }
}
