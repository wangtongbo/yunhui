package com.lakala.ls.ms.mapper;

import com.lakala.ls.ms.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	 public User loadUserByName(String username);

	    public void add(User user);
	    
	    public void update(User user);
	    
	    public void delete(User user);

	    public List<User> queryAll();
}