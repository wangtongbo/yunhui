package com.lakala.ls.ms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.AppContent;
import com.lakala.ls.ms.service.AppContentService;
import com.lakala.ls.util.FileUtils;

@RestController
@RequestMapping(value = "/content")
public class AppContentController {
	
	@Autowired
	private  AppContentService contentService;
	
	
	 @Value(value = "${product.webroot}")
	 private String webRoot;
	 private String url ="http://101.201.237.67/images";

	@RequestMapping(value = "/saveAppContent", method = RequestMethod.POST)
	@ResponseBody
	public void addProduct(@RequestParam("imgFile") MultipartFile excelFile,
			@RequestParam("name")String name,
			@RequestParam("type")String type,
			@RequestParam("addr")String addr,
			@RequestParam("rank")String rank,
			@RequestParam(value="productid",required=false)String productid
			) throws LsException {
		
		AppContent cont  = new AppContent();
		MultipartFile file = excelFile;
		String filePath = getFile();
		try {
			FileUtils.saveFileImputStream(file.getInputStream(), webRoot+filePath);
			cont.setImgurl(url+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		cont.setType(type);
		cont.setAddr(addr);
		cont.setName(name);
		cont.setRank(StringUtils.isEmpty(rank)?4:Long.parseLong(rank));
		cont.setState("0");
		if(productid!=null){
			cont.setProductid(Long.parseLong(productid));
		}
		contentService.addAppContent(cont);
	}

	private static String getFile() {
		String filepath ="/loms/static/adver/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date =sdf.format(new Date());
		StringBuffer sb=new StringBuffer();		
		sb.append(filepath).append(date).append("/");
		
		String name = UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
		sb.append(name);
		return sb.toString();
	}
	
		@RequestMapping(value = "/queryAppConts", method = RequestMethod.GET)
	    @ResponseBody
	    public List<AppContent> queryAppConts() throws LsException {
	    	return contentService.queryAppcontents();
	    }
	    
	    @RequestMapping(value = "/updateAppContentState", method = RequestMethod.POST)
	    @ResponseBody
	    public void updateAppContentState(@RequestBody Map map) throws LsException {
	    	String contid = (String)map.get("contid");
	    	Integer state = (Integer)map.get("state");
	    	contentService.updateContentState(Long.parseLong(contid), String.valueOf(state));
	    	
	    }
	    @RequestMapping(value = "/delcontent", method = RequestMethod.POST)
	    @ResponseBody
	    public void delcontent(@RequestBody Map map) throws LsException {
	    	String contid = (String)map.get("contid");
	    	contentService.deleteContent(Long.parseLong(contid));
	    }
	    
	    @RequestMapping(value = "/queryUserConts", method = RequestMethod.POST)
	    @ResponseBody
	    public List<AppContent> queryUserConts(@RequestBody Map map) throws LsException {
	    	String type =(String)map.get("type");
	    	String productid = (String)map.get("productid");
	    	if(productid==null){
	    		return contentService.queryUserConts(type);
	    	}else{
	    		return contentService.queryProductConts(type,Long.parseLong(productid));
	    	}
	    }
	    
	
}
