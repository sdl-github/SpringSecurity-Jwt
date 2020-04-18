package com.sdl.times.common.security.service;

import com.sdl.times.common.exception.CustomException;
import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.system.entity.User;
import com.sdl.times.system.service.MenuService;
import com.sdl.times.system.service.RoleService;
import com.sdl.times.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class MyUserDetailService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(MyUserDetailService.class);
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByName(userName);
        if (user == null){
            log.info("登录用户：{} 不存在.", userName);
            throw new CustomException("用户名不存在");
        }
        if (!user.getEnabled()){
            throw new AccessDeniedException("账户已禁用,请联系管理员！");
        }
        log.info("{}用户状态"+user.getEnabled(),userName);
        //查询权限
        menuService.findMenuByUserName(userName);
        return new SecurityUser(userName,user.getPassword(),roleService.findRoleByUserId(user.getId()));
    }
}
