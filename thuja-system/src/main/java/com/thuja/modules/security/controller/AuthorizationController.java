package com.thuja.modules.security.controller;

import cn.hutool.core.util.IdUtil;
import com.thuja.annotation.rest.AnonymousGetMapping;
import com.thuja.annotation.rest.AnonymousPostMapping;
import com.thuja.conf.RsaProperties;
import com.thuja.error.ReturnValue;
import com.thuja.exception.BadRequestException;
import com.thuja.modules.security.bean.LoginCodeEnum;
import com.thuja.modules.security.bean.LoginProperties;
import com.thuja.modules.security.bean.SecurityProperties;
import com.thuja.modules.security.config.TokenProvider;
import com.thuja.modules.security.service.dto.AuthUserDto;
import com.thuja.modules.security.service.dto.JwtUserDto;
import com.thuja.util.RedisUtils;
import com.thuja.util.RsaUtils;
import com.thuja.util.StringUtils;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private  AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private TokenProvider tokenProvider;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ReturnValue<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = authUser.getPassword();
//        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
//        // 查询验证码
//        String code = (String) redisUtils.get(authUser.getUuid());
//        // 清除验证码
//        redisUtils.del(authUser.getUuid());
//        if (StringUtils.isBlank(code)) {
//            throw new BadRequestException("验证码不存在或已过期");
//        }
//        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
//            throw new BadRequestException("验证码错误");
//        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌与第三方系统获取令牌方式
        // UserDetails userDetails = userDetailsService.loadUserByUsername(userInfo.getUsername());
        // Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication,authUser.isRememberMe());
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 保存在线信息
//        onlineUserService.save(jwtUserDto, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
//        if (loginProperties.isSingleLogin()) {
//            //踢掉之前已经登录的token
//            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
//        }
        return new ReturnValue(authInfo);
    }

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
