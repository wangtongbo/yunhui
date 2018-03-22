package com.lakala.ls.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.domain.UserLoad;
import com.lakala.ls.ms.mapper.UserLoadMapper;

@Service
public class UserLoadService {

	
	@Autowired
	private UserLoadMapper userloadmapper;
	
	
	public void addUserLoad(UserLoad userLoad){
		userloadmapper.addUserLoad(userLoad);
	}
	
}
