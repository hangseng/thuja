/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.thuja.modules.security.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.thuja.modules.system.service.dto.MenuSmallDto;
import com.thuja.modules.system.service.dto.RoleSmallDto;
import com.thuja.modules.system.service.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 */
@Getter
@AllArgsConstructor
public class JwtUserDto implements UserDetails {

    private final UserDto user;

//    private final List<Long> dataScopes;

    private final List<SimpleGrantedAuthority> authorities;
    public JwtUserDto(UserDto user){
        this.user = user;
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user.getIsAdmin()){
            authorities.add(new SimpleGrantedAuthority("admin"));
            this.authorities = authorities;
            return;
        }
        Set<MenuSmallDto> menus = user.getMenus();
        List<MenuSmallDto> noNullMenus = menus.stream().filter(Objects::nonNull).filter(s -> !s.getPermission().equals("")).collect(Collectors.toList());
        noNullMenus.forEach(ele -> authorities.add(new SimpleGrantedAuthority(ele.getPermission())) );
        this.authorities = authorities;
    }

    @JSONField(serialize = false)

    public Set<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return user.getUsername();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
