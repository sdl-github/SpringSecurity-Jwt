package com.sdl.times.common.security;

import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.system.entity.Menu;
import com.sdl.times.system.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;
/**
 * 动态权限处理
 */
@Component
public class DynamicPermission {
    private static final Logger log = LoggerFactory.getLogger(DynamicPermission.class);
    @Resource
    private MenuService menuService;

    public boolean checkPermission(HttpServletRequest request, Authentication auth) {
        Object principal = auth.getPrincipal();
        if(principal instanceof UserDetails){
            SecurityUser user = (SecurityUser) principal;
            //放在redis中，不能动态更改
          //  Set<String> apiUrl = user.getPermissons();
            //使用redis在数据库中读取权限
            Set<Menu> menus = menuService.findMenuByUserName(user.getUsername());
          //  Set<String> apiUrl = menus.stream().map(Menu::getApiUrl).collect(Collectors.toSet());

            /**
             *  url匹配
             * ？匹配一个字符
             * *匹配0个或多个字符
             * **匹配0个或多个目录
             */
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            //当前访问路径
            String requestURI = request.getRequestURI();
            boolean rs = menus.stream().anyMatch(item -> {
                //匹配api
                boolean hashPath = antPathMatcher.match(item.getApiUrl(),requestURI);
                //匹配methods，indexof如果要检索的字符串值没有出现，则该方法返回 -1。
                return hashPath;
            });
            log.info("此帐号拥有的api"+menus.toString());
            log.info("当前正在访问===>"+requestURI);
            log.info("是否匹配===>"+rs);
            if (rs) {
                return rs;
            }else {
                throw  new AccessDeniedException("您没有权限访问此接口！");
            }
        }else {
            throw  new AccessDeniedException("不是UserDetails类型！");
        }
    }

}

