package com.sdl.times.common.security.service;

import com.sdl.times.common.exception.user.MyAccessDeniedException;
import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.system.entity.User;
import com.sdl.times.system.service.RoleService;
import com.sdl.times.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByName(userName);
        if (user == null){
            log.info("登录用户：{} 不存在.", userName);
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!user.getEnabled()){
            throw new MyAccessDeniedException("账户已禁用,请联系管理员！");
        }
        log.info("{}用户状态"+user.getEnabled(),userName);

        return new SecurityUser(user,roleService.findRoleByUserId(user.getId()));
    }
}
