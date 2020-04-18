package com.sdl.times.system.controller;

import com.sdl.times.common.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sdl
 * @date 2020/4/9 9:32 下午
 * @description
 */
public class TestController {
    @RequestMapping("/test")
    public Result test(){
        return Result.success("success");
    }
}
