package com.sdl.times.system.service;

import com.sdl.times.system.entity.Menu;
import com.sdl.times.system.model.Router;

import java.util.List;
import java.util.Set;

/**
 * @author sdl
 * @date 2020/4/16 6:17 下午
 * @description
 */
public interface MenuService {
    public List<Menu> findMenuByUserId(Integer userId);

    public Set<Menu> findMenuByUserName(String userName);

    public List<Menu> buildMenu(Set<Menu> menuList);

    public List<Router> buildMenuRouter(List<Menu> menus);
}