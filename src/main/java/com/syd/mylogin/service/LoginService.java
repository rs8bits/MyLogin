package com.syd.mylogin.service;

import com.syd.mylogin.domain.ResponseResult;
import com.syd.mylogin.domain.User;
import org.springframework.stereotype.Service;


public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
