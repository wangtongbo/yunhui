package com.lakala.ls.ms.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.UserLoad;

@Mapper
public interface UserLoadMapper {
	
	public void addUserLoad(UserLoad load);
	
	
	public List<UserLoad> queryUserLoads(Date begindate,Date endDate);

	
}
