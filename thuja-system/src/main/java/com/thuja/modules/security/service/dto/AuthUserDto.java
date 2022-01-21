package com.thuja.modules.security.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author WangHengSheng
 * @date 2022/1/20 11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户密码不能为空")
    private String password;

    private String code;

    private boolean isRememberMe;

    private String uuid = "";
}
