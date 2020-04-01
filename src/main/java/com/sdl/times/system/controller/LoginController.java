package com.sdl.times.system.controller;

import com.sdl.times.common.model.Result;
import com.sdl.times.common.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public Result login(String username, String password){
        log.info(username+"正在登录");
        Result result = Result.success();
        String token = loginService.login(username,password);
        result.put("token",token);
        return result;
    }
}
