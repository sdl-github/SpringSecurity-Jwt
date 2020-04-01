package com.sdl.times.system.controller;

import com.sdl.times.common.model.Result;
import com.sdl.times.system.entity.User;
import com.sdl.times.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("userlist")
    public Result userlist(){
        List<User> users = userService.getuserList();
        return Result.success(users);
    }
    @GetMapping("{id}")
    public Result getuser(@PathVariable(value = "id") Integer id){
        User user = userService.findById(id);
        return Result.success(user);
    }
}
