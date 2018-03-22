package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.AppContent;

@Mapper
public interface AppContentMapper {
	public int addAppContent(AppContent cont)throws LsException;
	
	public List<AppContent> queryAppContents()throws LsException;
	
	public int updateAppContentState(long id,String state)throws LsException;

	public int deleteAppContent(long id)throws LsException;
	
	public List<AppContent> queryUserAppContents(String type)throws LsException;
	
	public List<AppContent> queryProductContents(String type,long productid)throws LsException;
	
}
