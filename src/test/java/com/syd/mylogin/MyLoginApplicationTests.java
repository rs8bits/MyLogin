package com.syd.mylogin;

import com.syd.mylogin.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MyLoginApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectById(2));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = passwordEncoder.encode("123456");
        System.out.println(pwd);
    }

}
