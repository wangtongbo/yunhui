package com.lakala.ls.ms.service;

import com.lakala.ls.ms.domain.User;
import com.lakala.ls.ms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenjian on 16/5/16.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void add(User user){
        userMapper.add(user);
    }
    
    public void update(User user){
        userMapper.update(user);
    }
    
    public User loadUserByName(String name){
        User user = userMapper.loadUserByName(name);
        return user;
    }

    public List<User> loadAllUsers(){
        return userMapper.queryAll();
    }

    public void delete(User user){
        userMapper.delete(user);
    }
    

}
