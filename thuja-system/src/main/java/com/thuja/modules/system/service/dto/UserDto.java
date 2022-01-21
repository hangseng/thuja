package com.thuja.modules.system.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.thuja.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author WangHengSheng
 * @date 2022/1/20 14:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDTO implements Serializable {
    private Long id;

    private Set<RoleSmallDto> roles;

    private Set<JobSmallDto> jobs;

    private DeptSmallDto dept;

    private Long deptId;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JSONField(serialize = false)
    private String password;

    private Boolean enabled;

    @JSONField(serialize = false)
    private Boolean isAdmin = false;

    private Date pwdResetTime;
}
