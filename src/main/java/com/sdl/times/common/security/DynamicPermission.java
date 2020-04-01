package com.sdl.times.common.security;

import com.sdl.times.common.exception.user.MyAccessDeniedException;
import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.system.entity.Menu;
import com.sdl.times.system.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 动态权限处理
 */
@Component
public class DynamicPermission {
    private static final Logger log = LoggerFactory.getLogger(DynamicPermission.class);
    @Autowired
    private MenuService menuService;
    public boolean checkPermission(HttpServletRequest request, Authentication auth) {
        Object principal = auth.getPrincipal();
        if(principal instanceof UserDetails){
            SecurityUser user = (SecurityUser) principal;
            List<Menu> menus = menuService.findMenuByUserName(user.getUsername());
            log.info("数据库中权限menu"+menus);
            //url匹配 /**
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            //当前访问路径
            String requestURI = request.getRequestURI();
            log.info(user.getUsername()+"当前访问"+requestURI);
            boolean rs = menus.stream().anyMatch(item -> {
                boolean hashPath = antPathMatcher.match(item.getUrl(),requestURI);
                return hashPath;
            });
            log.info("是否匹配===>"+rs);
            if (rs) {
                return rs;
            }else {
                throw  new MyAccessDeniedException("您没有访问此api的权限!");
            }
        }else {
            throw  new AccessDeniedException("不是UserDetails类型！");
        }
    }

}

