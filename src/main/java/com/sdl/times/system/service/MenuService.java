package com.sdl.times.system.service;

import com.sdl.times.system.dao.MenuDao;
import com.sdl.times.system.entity.Menu;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    MenuDao menuDao;
    public List<Menu> findMenuByUserId(Integer userId){
        return menuDao.queryByUserId(userId);
    }
    public List<Menu> findMenuByUserName(String userName){
        return menuDao.queryByUserName(userName);
    }
}
