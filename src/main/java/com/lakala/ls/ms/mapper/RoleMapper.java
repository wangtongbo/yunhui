package com.lakala.ls.ms.mapper;

import com.lakala.ls.ms.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chenjian on 16/5/16.
 */
@Mapper
public interface RoleMapper {

	  public List<Role> getByUserId(long userId);

	    public List<Role> getAllRoles();

	    public void saveUserRole(@Param("userName") String userName, @Param("role") String role);
	    
	    public void deleteUserRole(@Param("userName") String userName);
}
