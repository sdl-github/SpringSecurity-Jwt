package com.sdl.times.system.service;
import com.sdl.times.system.entity.User;
import java.util.List;

/**
 * @author sdl
 * @date 2020/4/16 6:10 下午
 * @description
 */
public interface UserService {
    /**
     * 根据userName查找User
     * @param userName
     * @return
     */
    public User findUserByName(String userName);

    /**
     * 获得userList
     * @return
     */
    public List<User> getuserList();

    /**
     * 根据id查找User
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUser(User user);
}
