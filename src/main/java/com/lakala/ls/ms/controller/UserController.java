package com.lakala.ls.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.Current;
import com.lakala.ls.ms.domain.Role;
import com.lakala.ls.ms.domain.User;
import com.lakala.ls.ms.service.RoleService;
import com.lakala.ls.ms.service.UserService;
import com.lakala.ls.util.PasswordEncryptUtil;


/**
 * Created by chenjian on 16/5/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public void createUser(@Validated @RequestBody User user) throws LsException{
        User absentUser = userService.loadUserByName(user.getUserName());
        if(absentUser!=null){
            throw new LsException("username_aleady_exits");
        }
        user.setPassword(PasswordEncryptUtil.encrypt(user.getPassword()));
        user.setState(User.State.OK);
        userService.add(user);
        roleService.saveUserRoles(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public User info(){
        return Current.get();
    }

    @RequestMapping(value = "/roles",method = RequestMethod.POST)
    public List<Role> getRoles(@RequestBody User user){
        if(StringUtils.isEmpty(user.getId())) {
            User cacheUser = Current.get();
            return roleService.getByUserId(cacheUser.getId());
        } else {
            return roleService.getByUserId(user.getId());
        }
    }

    @RequestMapping(value = "/getAllRoles",method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.loadAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public int updateUser(@RequestBody User user) throws LsException{
        try{
            userService.update(user);
            roleService.updateUserRoles(user);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/updateUserState", method = RequestMethod.POST)
    public int updateUserState(@RequestBody User user) throws LsException{
        try{
            userService.update(user);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public int deleteUser(@RequestBody User user) throws LsException{
        try{
            roleService.deleteUserRoles(user);
            userService.delete(user);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/updUserPwd", method = RequestMethod.POST)
    public int updUserPwd(@RequestBody User user) throws LsException{
        String userName = Current.get().getUserName();
        User oldUser = userService.loadUserByName(userName);
        if(!PasswordEncryptUtil.match(user.getOldPwd(), oldUser.getPassword())) {
            return 2;
        }
        user.setPassword(PasswordEncryptUtil.encrypt(user.getPassword()));
        user.setUserName(userName);
        userService.update(user);
        return 1;
    }

}
