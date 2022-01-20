package com.thuja.modules.security.controller;

import cn.hutool.core.util.IdUtil;
import com.thuja.annotation.rest.AnonymousGetMapping;
import com.thuja.error.ReturnValue;
import com.thuja.modules.security.bean.LoginCodeEnum;
import com.thuja.modules.security.bean.LoginProperties;
import com.thuja.modules.security.bean.SecurityProperties;
import com.thuja.util.RedisUtils;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author WangHengSheng
 *授权、根据token获取用户详细信息
 * @date 2022/1/20 8:58
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {
    @Resource
    private LoginProperties loginProperties;
    @Resource
    private SecurityProperties properties;
    @Resource
    private RedisUtils redisUtils;

    @ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public ReturnValue<Map<String,Object>> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return new ReturnValue<>(imgResult);
    }
}
