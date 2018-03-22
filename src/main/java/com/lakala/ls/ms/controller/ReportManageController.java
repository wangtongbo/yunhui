package com.lakala.ls.ms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lakala.ls.ms.service.ReportManageService;


@RestController
@RequestMapping(value = "/report")
public class ReportManageController {

    @Autowired
    private ReportManageService reportManageService;

    /**
     * 查询对账日报
     * @return
     */

    @RequestMapping(value = "/listCheckDayReport", method = RequestMethod.POST)
    @ResponseBody
    public List listCheckDayReport(@RequestBody Map map) {
    	String d1 = (String)map.get("beginDate");
    	String d2 = (String)map.get("endDate");
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			Date begin = simpleDateFormat.parse(d1 + "000000");
			Date end = simpleDateFormat.parse(d2 + "235959");
			return reportManageService.queryCheckReportDayList(begin, end);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
