package com.chm.controller;

import com.chm.entity.User;
import com.chm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());//日志

    @Autowired
    private UserService userService;

    /*
     * admin权限：查询所有用户
     * @param user
     * @return
     */
    @GetMapping("/users")
    public String getUsers(String username,
                           Map<String, Object> map,
                           @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        System.out.println("admin查询所有用户...");
        PageHelper.startPage(pageNum, 10);
        List<User> userList = userService.getUsers(username);
        PageInfo<User> users = new PageInfo<>(userList);
        map.put("users", users);
        map.put("username", username);//回显查询条件到输入框
        return "admin/listUser";
    }


    /*
     * admin权限："用户详情" + "修改"
     * 1.查看用户详情（根据id查询用户）
     * 2.前往修改页面
     * @param map
     * @param id
     * @param type
     * @return
     */
    @GetMapping("/users/{id}")
    public String getUserById(Map<String, Object> map,
                              @PathVariable("id") Integer id,
                              @RequestParam(name = "type", defaultValue = "viewUser") String type) {
        User user = userService.getUserById(id);
        map.put("user", user);
        return "admin/" + type;
    }


    /*
     * admin权限：修改用户信息
     * @param user
     * @return
     */
    @PutMapping("/users")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }


    /*
     * admin权限：前往用户添加页面
     * @return
     */
    @GetMapping("/user")//注意：此处映射路径为'user'，而非'users'。
    public String toAddUser() {
        return "admin/addUser";
    }


    /*
     * admin权限：添加用户
     * @param user
     * @return
     */
    @PostMapping("/users")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }


    /*
     * admin权限：删除指定用户
     * @param id
     * @return
     */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        logger.info("删除帐号为 " + user.getUsername() + " 的用户...");
        userService.deleteUser(id);
        return "redirect:/users";
    }


    /* =============================================================================================== */
    /* ======================================== 以下为通用权限 ========================================= */
    /* =============================================================================================== */
    /*
     * 前往密码修改页面
     * @return
     */
    @GetMapping("/user/pwd")
    public String toUpdatePwdPage() {
        System.out.println("修改密码...");
        return "main/password";
    }


    /*
     * 发送AJAX请求，检验输入的旧密码是否正确
     * @param oldPwd
     * @param authentication
     * @return
     */
    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPwd(@PathVariable("oldPwd") String oldPwd, Authentication authentication) {
        logger.info("旧密码：" + oldPwd);
        User user = (User) userService.loadUserByUsername(authentication.getName());
        if (oldPwd.equals(user.getPassword())) {
            return true;
        }
        return false;
    }


    /*
     * 修改密码
     * @param authentication
     * @param password
     * @return
     */
    @PostMapping("/user/pwd")
    public String updatePwd(Authentication authentication, String password) {
        //1.从authentication对象中获取当前登录用户的信息
        User user = (User) userService.loadUserByUsername(authentication.getName());
        //2.更新密码
        user.setPassword(password);
        //3.提交到数据库
        userService.updateUser(user);//密码更新之后，Spring Security会自动注销当前用户并让用户进行重新登录
        return "redirect:/gotoLogin";
    }
}
