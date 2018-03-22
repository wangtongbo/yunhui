package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.RankManager;

@Mapper
public interface RankManagerMapper {
	
	public void addRank(RankManager rank);
	
	public List<RankManager> queryRanks();
	
	public void updateRank(RankManager rank);
	public void deleteRank(long id);

	public void deleteRankByProductid(long proid);
}
