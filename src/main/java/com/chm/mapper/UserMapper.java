package com.chm.mapper;

import com.chm.entity.Role;
import com.chm.entity.User;

import java.util.List;

public interface UserMapper {

    /* 根据名称查询用户（用于登录身份校验） */
    User loadUserByUsername(String username);

    User getUserById(Integer id);

    /* 根据用户id查询其对应的角色 */
    List<Role> getRolesByUserId(Integer id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 删除用户
     * @param uid
     * @return
     */
    int deleteUser(Integer uid);

    /*
     * admin权限：查询所有用户
     * @param user
     * @return
     */
    List<User> getUsers(String username);

}
