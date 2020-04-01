package com.sdl.times.system.service;

import com.sdl.times.system.dao.UserDao;
import com.sdl.times.system.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    public User findUserByName(String userName){
        return userDao.queryByName(userName);
    }
    public List<User> getuserList(){
        return userDao.queryAll(null);
    }
    public User findById(Integer id){
        return userDao.queryById(id);
    }
}
