package com.sdl.times.system.service.impl;

import com.sdl.times.common.utils.StringUtil;
import com.sdl.times.system.dao.MenuDao;
import com.sdl.times.system.entity.Menu;
import com.sdl.times.system.model.Meta;
import com.sdl.times.system.model.Router;
import com.sdl.times.system.service.MenuService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;


@Service
@CacheConfig(cacheNames = "Api")
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuDao menuDao;
    public List<Menu> findMenuByUserId(Integer userId){
        return menuDao.queryByUserId(userId);
    }
    @Cacheable(key = "'Permission:' + #p0")
    public Set<Menu> findMenuByUserName(String userName){
        Set<Menu> menus = menuDao.queryByUserName(userName);
        return menus;
    }
    /**
     * 获取子节点
     * @param id 父节点id
     * @param menus 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<Menu> getChild(Integer id,Set<Menu> menus){
        List<Menu> child = new ArrayList<>();
        for (Menu nav : menus ) {
            if (nav.getPid().equals(id)) {
                child.add(nav);
            }
        }
        for (Menu sub : child) {
            sub.setChildren(getChild(sub.getId(),menus));
        }
        if (child.size() == 0) {
            return new ArrayList<Menu>();
        }
        Collections.sort(child);
        return child;
    }
    @Override
    public List<Menu> buildMenu(Set<Menu> menuList) {
        List<Menu> rootMenu = new ArrayList<>();
        for (Menu item : menuList) {
            if (item.getType().equals("0")) {
                rootMenu.add(item);
            }
        }
        for (Menu menu : rootMenu) {
            List<Menu> subMenu = getChild(menu.getId(),menuList);
            menu.setChildren(subMenu);
        }
        Collections.sort(rootMenu);
       return rootMenu;
    }

    @Override
    public List<Router> buildMenuRouter(List<Menu> menus) {
        List<Router> routerList = new LinkedList<>();
        for (Menu menu : menus) {
            Router router = new Router();
            router.setName(menu.getName());
            router.setPath(menu.getApiUrl());
            router.setHidden(menu.getHidden());
            router.setComponent(StringUtil.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new Meta(menu.getName(),menu.getIcon()));
            List<Menu> subMenu = menu.getChildren();
            if (!subMenu.isEmpty() &&subMenu.size()>0 && menu.getType().equals("0") ) {
                router.setRedirect("noRedirect");
                router.setChildren(buildMenuRouter(subMenu));
            }
            routerList.add(router);
        }
        return routerList;
    }
}
