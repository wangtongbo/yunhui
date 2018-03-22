package com.lakala.ls.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.AppContent;
import com.lakala.ls.ms.mapper.AppContentMapper;

@Service
public class AppContentService {
	
	@Autowired
	private  AppContentMapper contentMapper;
	
	public int addAppContent(AppContent cont)throws LsException{
		
		return contentMapper.addAppContent(cont);
	}
	
	public List<AppContent> queryAppcontents()throws LsException{
		
		return contentMapper.queryAppContents();
		
	}

	public void updateContentState(Long id ,String state)throws LsException{
		
		 contentMapper.updateAppContentState(id, state);
	}
	
	public void deleteContent(long id )throws LsException{
		contentMapper.deleteAppContent(id);
	}
	
	
	public List<AppContent> queryUserConts(String type)throws LsException{
		
		return contentMapper.queryUserAppContents(type);
	}
	
	public List<AppContent> queryProductConts(String type,long productid)throws LsException{
		
		return contentMapper.queryProductContents(type, productid);
	}
}
