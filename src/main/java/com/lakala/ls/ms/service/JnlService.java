package com.lakala.ls.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.domain.Jnl;
import com.lakala.ls.ms.mapper.JnlMapper;

@Service
public class JnlService {
	
	@Autowired
	private JnlMapper jnlMapper;
	
	
	public void addJnl(Jnl jnl){
		jnlMapper.addjnl(jnl);
	}
	
	

}
