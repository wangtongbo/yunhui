package com.lakala.ls.ms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.Jnl;

@Mapper
public interface JnlMapper {
	
	public void addjnl(Jnl jnl);

}
