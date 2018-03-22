package com.lakala.ls.ms.mapper;

import java.util.List;

import com.lakala.ls.ms.dto.CheckBatchDTO;
import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.dto.ChannelDTO;


@Mapper
public interface CheckBatchMapper {

    public Integer deleteCheckBatch(CheckBatchDTO dto);

    public Integer insertCheckBatch(CheckBatchDTO dto);

    public Integer updateCheckBatch(CheckBatchDTO dto);
}