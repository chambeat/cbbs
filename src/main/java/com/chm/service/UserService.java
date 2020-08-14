package com.chm.service;

import com.chm.entity.User;
import com.chm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户的业务层(实现)类
 */
@Service
@Transactional
//@EnableTransactionManagement
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        // 获取用户的角色
        user.setRoles(userMapper.getRolesByUserId(user.getId()));
        return user;
    }

    /* admin权限：查询所有用户 */
    public List<User> getUsers(String username) {
        return userMapper.getUsers(username);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    public int deleteUser(Integer uid) {
        return userMapper.deleteUser(uid);
    }
}
