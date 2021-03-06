package com.thuja.modules.security.filter;

import com.thuja.modules.security.config.TokenProvider;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WangHengSheng
 * @date 2022/1/19 14:41
 */

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenProvider tokenProvider;
    private final StringRedisTemplate stringRedisTemplate;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate,TokenProvider tokenProvider) {
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;
        this.tokenProvider = tokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        String tokenValue = token.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
//            String previousToken = stringRedisTemplate.opsForValue().get(JwtTokenUtils.getId(tokenValue));
//            if (!token.equals(previousToken)) {
//                SecurityContextHolder.clearContext();
//                chain.doFilter(request, response);
//                return;
//            }
             authentication = tokenProvider.getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
