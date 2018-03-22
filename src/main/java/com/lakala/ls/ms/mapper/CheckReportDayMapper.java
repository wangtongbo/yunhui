package com.lakala.ls.ms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.dto.CheckRepotDayDTO;


@Mapper
public interface CheckReportDayMapper {

    public List<CheckRepotDayDTO> countLoanBillNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countLoanUserNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countNewLoanSuccessNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countAgainLoanSuccessNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countNewUserSuccessNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countAgainUserSuccessNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countNewLoanSuccessAmountNum(String clearDate);

    public List<CheckRepotDayDTO> countAgainLoanSuccessAmountNum(String clearDate);

    public List<CheckRepotDayDTO> countNewLoanProfitNumByProduct(String clearDate);

    public List<CheckRepotDayDTO> countAgainLoanProfitNumByProduct(String clearDate);

    public Integer saveCheckReportDayList(List<CheckRepotDayDTO> reportDayList);

    public List<CheckRepotDayDTO> queryCheckReportDayList(Map map);

}