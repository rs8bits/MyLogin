package com.syd.mylogin.controller;

import com.syd.mylogin.domain.ResponseResult;
import com.syd.mylogin.domain.User;
import com.syd.mylogin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
   @Autowired
   LoginService loginService;
   @PostMapping("/user/login")
    ResponseResult login(@RequestBody User user){
       return loginService.login(user);
   }

   @RequestMapping("/user/logout")
   ResponseResult logout(){
      return loginService.logout();
   }

}
