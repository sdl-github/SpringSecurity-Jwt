package com.sdl.times.system.service;

import com.sdl.times.system.entity.Role;

import java.util.List;

/**
 * @author sdl
 * @date 2020/4/16 6:16 下午
 * @description
 */
public interface RoleService {
    public List<Role> findRoleByUserId(Integer userId);
}
