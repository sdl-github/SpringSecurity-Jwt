package com.sdl.times;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdl.times.common.utils.StringUtil;
import com.sdl.times.system.entity.Menu;
import com.sdl.times.system.model.Meta;
import com.sdl.times.system.model.Router;
import com.sdl.times.system.service.MenuService;
import com.sdl.times.system.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sdl
 * @date 2020/4/15 1:29 下午
 * @description
 */
@SpringBootTest(classes = TimesApplication.class)
public class MenuTest {
    Logger log =  LoggerFactory.getLogger(MenuTest.class);
    @Autowired
    MenuService menuService;

    /**
     * 获取子节点
     * @param id 父节点id
     * @param menus 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<Menu> getChild(Integer id,Set<Menu> menus){
        List<Menu> child = menus.stream().filter((Menu m) -> m.getPid().equals(id)).collect(Collectors.toList());

        for (Menu sub : child) {
            sub.setChildren(getChild(sub.getId(),menus));
        }
        if (child.size() == 0) {
            return new ArrayList<Menu>();
        }
        Collections.sort(child);
        return child;
    }
    @Test
    void testMenu(){
        Set<Menu> menus = menuService.findMenuByUserName("admin");
        List<Menu> rootMenu = new ArrayList<>();
        for (Menu item : menus) {
            if (item.getType().equals("0")) {
                rootMenu.add(item);
            }
        }
        log.info(rootMenu.toString());
       for (Menu menu : rootMenu) {
            List<Menu> subMenu = getChild(menu.getId(),menus);
            menu.setChildren(subMenu);
       }
       Collections.sort(rootMenu);
       // System.out.println( JSONObject.toJSONString(menus));
        //log.info(rootMenu.toString());
        System.out.println(JSONObject.toJSONString(rootMenu));
        List<Router> routerList = buildrouter(rootMenu);
        System.out.println(JSONObject.toJSONString(routerList));
    }
    public List<Router> buildrouter(List<Menu> menus) {
        List<Router> routerList = new LinkedList<>();
        for (Menu menu : menus) {
            Router router = new Router();
            router.setName(menu.getName());
            router.setPath(menu.getApiUrl());
            router.setComponent(StringUtil.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new Meta(menu.getName(),menu.getIcon()));
            List<Menu> subMenu = menu.getChildren();
            if (!subMenu.isEmpty() &&subMenu.size()>0 && menu.getType().equals("0") ) {
                router.setRedirect("noRedirect");
                router.setChildren(buildrouter(subMenu));
            }
            routerList.add(router);
        }
        return routerList;
    }
    @Test
    void test(){

    }

}
