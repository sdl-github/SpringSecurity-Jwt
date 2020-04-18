package com.sdl.times.system.controller;

import com.sdl.times.common.constant.HttpStatus;
import com.sdl.times.common.exception.CustomException;
import com.sdl.times.common.model.Result;
import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.common.security.service.JwtService;
import com.sdl.times.common.security.service.LoginService;
import com.sdl.times.common.utils.ServletUtil;
import com.sdl.times.system.entity.Menu;
import com.sdl.times.system.model.LoginUser;
import com.sdl.times.system.model.Router;
import com.sdl.times.system.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MenuService menuService;
    @PostMapping("/login")
    public Result login(@RequestBody LoginUser loginUser){
        log.info(loginUser.getUsername()+"正在登录");
        Result result = Result.success();
        String token = loginService.login(loginUser.getUsername(),loginUser.getPassword());
        result.put("token",token);
        return result;
    }
    /**
     * 获取用户，角色，权限信息
     * @return
     */
    @GetMapping("/getInfo")
    public Result getUserinfo(){
        SecurityUser loginUser = jwtService.getLoginUser(ServletUtil.getRequest());
        if (loginUser == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED,"认证失败，无法访问系统资源");
        }
        return Result.success();
    }
    /**
     * 获取当前用户前端菜单
     * @return
     */
    @GetMapping("/getMenu")
    public Result getMenu() {
        SecurityUser loginUser = jwtService.getLoginUser(ServletUtil.getRequest());
        if (loginUser == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED,"认证失败，无法访问系统资源");
        }
        Set<Menu> menus = menuService.findMenuByUserName(loginUser.getUsername());
        List<Menu> menuList = menuService.buildMenu(menus);
        List<Router> routerList = menuService.buildMenuRouter(menuList);
        return Result.success(routerList);
    }

}
