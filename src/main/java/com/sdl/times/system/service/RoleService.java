package com.sdl.times.system.service;

import com.sdl.times.system.dao.RoleDao;
import com.sdl.times.system.entity.Role;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    RoleDao roleDao;
    public List<Role> findRoleByUserId(Integer userId){
       return roleDao.queryByUserId(userId);
    }
}
