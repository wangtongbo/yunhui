package com.lakala.ls.ms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.domain.UserLoad;
import com.lakala.ls.ms.mapper.UserLoadMapper;

@Service
public class ReportManageService {

	@Autowired
	private UserLoadMapper userloadMapper;

	public List queryCheckReportDayList(Date begin,Date end) {
		
		
		List<UserLoad> list =userloadMapper.queryUserLoads(begin, end);
		
		List res = new ArrayList<>();
		
		List <UserLoad>outList =new ArrayList<>(list);
		
		
		for(int i=outList.size()-1;i>=0;i--){
			UserLoad ul = outList.get(i);
			Map map = new HashMap();
		
			List inlist = new ArrayList<>();
			
			for (int k=outList.size()-1;k>=0;k--){
				UserLoad inul = outList.get(k);
				Map mp = new HashMap();
				if(ul.getLoadDate().equals(inul.getLoadDate())){
					int m=0;
					boolean flag= false;
					for (int n=list.size()-1;n>=0;n--){
						UserLoad tul = list.get(n);
						if(tul.getProductId()==inul.getProductId()&&inul.getLoadDate().equals(tul.getLoadDate())){
							m++;
							flag=true;
							list.remove(n);
						}
					}
					if(flag){
						mp.put("productid", inul.getProductId());
						mp.put("productname", inul.getProductName());
						mp.put("count", m);
						inlist.add(mp);
					}
				
					
				}
				
			}
			if(inlist.size()>0){
				map.put("list", inlist);
				map.put("resDate", ul.getLoadDate());
			}
			if(!map.isEmpty()){
				res.add(map);
			}
		
		}
		
		
		return res;
	}

}
