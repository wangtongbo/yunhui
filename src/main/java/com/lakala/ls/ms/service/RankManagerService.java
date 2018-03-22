package com.lakala.ls.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.domain.RankManager;
import com.lakala.ls.ms.mapper.RankManagerMapper;

@Service
public class RankManagerService {
	
	@Autowired
	private RankManagerMapper rankMapper;
	
	public void addRank(RankManager rank){
		
		rankMapper.addRank(rank);
	}
	
	public void deleteRank(long id ){
		
		rankMapper.deleteRank(id);
	}
	
	public List<RankManager> queryRanks(){
		
		return rankMapper.queryRanks();
	}
	
	public void updateRank(RankManager rank){
		rankMapper.updateRank(rank);
	}

}
