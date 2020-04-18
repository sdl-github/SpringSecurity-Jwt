package com.sdl.times.system.service.impl;

import com.sdl.times.system.dao.UserDao;
import com.sdl.times.system.entity.User;
import com.sdl.times.system.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Cacheable(key = "'userinfo:'+#userName")
    public User findUserByName(String userName){
        return userDao.queryByName(userName);
    }
    public List<User> getuserList(){
        return userDao.queryAll(null);
    }
    public User findById(Integer id){
        return userDao.queryById(id);
    }
    @CacheEvict(allEntries = true)
    public int updateUser(User user){
        return userDao.update(user);
    }
}
