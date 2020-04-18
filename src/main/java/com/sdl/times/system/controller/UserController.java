package com.sdl.times.system.controller;

import com.sdl.times.common.model.Result;
import com.sdl.times.system.entity.User;
import com.sdl.times.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("system/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public Result userlist(){
        List<User> users = userService.getuserList();
        return Result.success(users);
    }
    @GetMapping("/list/{id}")
    public Result getuser(@PathVariable(value = "id") Integer id){
        User user = userService.findById(id);
        return Result.success(user);
    }
    @PutMapping("/edit")
    public Result edituser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }
}
