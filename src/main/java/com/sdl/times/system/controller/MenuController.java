package com.sdl.times.system.controller;

import com.sdl.times.common.model.Result;
import com.sdl.times.common.security.service.JwtService;
import com.sdl.times.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * @author sdl
 * @date 2020/4/15 1:19 下午
 * @description
 */

@RestController
public class MenuController {
    @Resource
    private MenuService menuService;
    @Autowired
    private JwtService jwtService;




}
