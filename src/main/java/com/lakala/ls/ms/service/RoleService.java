package com.lakala.ls.ms.service;

import com.lakala.ls.ms.domain.Role;
import com.lakala.ls.ms.domain.User;
import com.lakala.ls.ms.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenjian on 16/5/16.
 */
@Service
public class RoleService {

	 @Autowired
	    private RoleMapper roleMapper;

	    public List<Role> getByUserId(long userId){
	        return roleMapper.getByUserId(userId);
	    }

	    public List<Role> getAllRoles(){
	        return roleMapper.getAllRoles();
	    }

	    public void saveUserRoles(User user){
	        List<Role> roles = user.getRoles();
	        for(Role role:roles){
	            roleMapper.saveUserRole(user.getUserName(),role.getCode());
	        }
	    }
	    
	    public void updateUserRoles(User user){
	    	roleMapper.deleteUserRole(user.getUserName());
	    	
	        List<Role> roles = user.getRoles();
	        for(Role role:roles){
	            roleMapper.saveUserRole(user.getUserName(),role.getCode());
	        }
	    }
	    
	    public void deleteUserRoles(User user){
	    	roleMapper.deleteUserRole(user.getUserName());
	    }

}
