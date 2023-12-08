package com.syd.mylogin.service.Impl;

import com.syd.mylogin.domain.LoginUser;
import com.syd.mylogin.domain.ResponseResult;
import com.syd.mylogin.domain.User;
import com.syd.mylogin.service.LoginService;
import com.syd.mylogin.utils.JwtUtil;
import com.syd.mylogin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName()
                ,user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败！");
        }

        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        String userId = principal.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login"+userId,principal);
        return new ResponseResult( 200,"登陆成功",map);
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisCache.deleteObject("login"+id);
        return new ResponseResult(200,"注销成功！");
    }
}
