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
package com.thuja.modules.system.service.impl;

import com.thuja.exception.EntityNotFoundException;
import com.thuja.modules.system.domain.Dept;
import com.thuja.modules.system.domain.Job;
import com.thuja.modules.system.domain.Role;
import com.thuja.modules.system.domain.User;
import com.thuja.modules.system.mapper.UserMapper;
import com.thuja.modules.system.service.UserService;
import com.thuja.modules.system.service.dto.DeptSmallDto;
import com.thuja.modules.system.service.dto.JobSmallDto;
import com.thuja.modules.system.service.dto.RoleSmallDto;
import com.thuja.modules.system.service.dto.UserDto;
import com.thuja.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    private final RedisUtils redisUtils;


    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public void create(User resources) {

    }

    @Override
    public void update(User resources) throws Exception {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public UserDto findByName(String userName) {
        UserDto userDto = userMapper.findByUsername(userName);
        if (userDto == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userDto;
        }
    }

    @Override
    public void updatePass(String username, String encryptPassword) {

    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public void updateEmail(String username, String email) {

    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {

    }


}
