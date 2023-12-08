package com.syd.mylogin.handler.security;

import com.alibaba.fastjson.JSON;

import com.syd.mylogin.domain.ResponseResult;
import com.syd.mylogin.enums.AppHttpCodeEnum;
import com.syd.mylogin.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult result = null;

        result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),"用户认证失败");

        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
