package com.lakala.ls.ms.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.domain.UserLoad;
import com.lakala.ls.ms.dto.DatePageRequest;
import com.lakala.ls.ms.service.ProductService;
import com.lakala.ls.ms.service.UserLoadService;
import com.lakala.ls.util.FileUtils;

@RestController
@RequestMapping(value = "/product")
public class ProductContoller {
	
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private UserLoadService userloadService;
	 
	 @Value(value = "${product.webroot}")
	 private String webRoot;
	 
	 private String url ="http://101.201.237.67/images";

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	@ResponseBody
	public void addProduct(@RequestParam("imgFile") MultipartFile excelFile,
			@RequestParam(value="imgFile1",required=false) MultipartFile excelFile1,
			@RequestParam(value="imgFile2",required=false) MultipartFile excelFile2,
			@RequestParam(value="imgFile3",required=false) MultipartFile excelFile3,
			@RequestParam(value="imgFile4",required=false) MultipartFile excelFile4,
			@RequestParam(value="imgFile5",required=false) MultipartFile excelFile5,		
			@RequestParam("productName")String productName,
			@RequestParam("productTitle")String productTitle,
			@RequestParam("description")String description,
			@RequestParam("category")String category,
			@RequestParam("score")String score,
			@RequestParam("level")String level,
			@RequestParam("type")String type,
			@RequestParam("addr")String addr,
			@RequestParam("version")String version,
			@RequestParam("dimension")String dimension,
			@RequestParam("merchantId")String merchantId
			) throws LsException {
		
	    
		MultipartFile file = excelFile;
		String logo="/loms/static/www/loms/";
		String filePath = getFile(logo);
		try {
			FileUtils.saveFileImputStream(file.getInputStream(), webRoot+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
		Product pro  = new Product();
		pro.setCategoryid(Long.parseLong(category));
		pro.setDescription(description);
		pro.setLevel(level);
		pro.setProductName(productName);
		pro.setProductTitle(productTitle);
		pro.setScore(score);
		pro.setType(type);
		pro.setAddr(addr);
		pro.setImgurl(url+filePath);
		pro.setState("0");
		pro.setVersion(version);
		pro.setRank(2L);
		pro.setDimension(dimension);
		pro.setMerchantId(Long.parseLong(merchantId));
		
		if(excelFile1!=null){
			String f1 = saveProd(excelFile1);
			pro.setImg1(f1);
		}
		if(excelFile2!=null){
			String f12 = saveProd(excelFile2);
			pro.setImg2(f12);
		}
		
		if(excelFile3!=null){
			String f3 = saveProd(excelFile3);
			pro.setImg3(f3);
		}
		
		if(excelFile4!=null){
			String f4 = saveProd(excelFile4);
			pro.setImg4(f4);
		}
		
		if(excelFile5!=null){
			String f5 = saveProd(excelFile5);
			pro.setImg5(f5);
		}
		
		proService.addProduct(pro);
		
	}
	
	private String saveProd(MultipartFile file){
		String prod="/loms/static/prod/";
		String filePath1 = getFile(prod);
		try {
			FileUtils.saveFileImputStream(file.getInputStream(), webRoot+filePath1);;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url+filePath1;
	}
	
	
	
	

	
	

	private static String getFile(String filepath) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date =sdf.format(new Date());
		StringBuffer sb=new StringBuffer();		
		sb.append(filepath).append(date).append("/");
		
		String name = UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
		sb.append(name);
		return sb.toString();
	}
	

	@RequestMapping(value = "/queryProducts", method = RequestMethod.POST)
	@ResponseBody
	public Map queryProducts(@RequestBody DatePageRequest request) throws LsException{

		Map resultMap = new HashMap();
        if(request.getPageSize() != null) {
            request.setPageIndex(request.getPageSize() * (request.getPageNum() - 1));
        }
        
        List list= proService.listProducts(request);
        resultMap.put("proList", list);

        int count = proService.listProCount(request);
        resultMap.put("count", count);

        return resultMap;

		
	}
	
	
	@RequestMapping(value = "/updateProductState", method = RequestMethod.POST)
    @ResponseBody
    public void updateProductState(@RequestBody Map map) throws LsException {
    	String prodId = (String)map.get("prodId");
    	String state = (String)map.get("state");
    	proService.updateProductState(state, Long.parseLong(prodId));
    }
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	@ResponseBody
	public void updateProduct(@RequestParam(value="imgFile",required=false) MultipartFile excelFile,
			@RequestParam("productName")String productName,
			@RequestParam(value="imgFile1",required=false) MultipartFile excelFile1,
			@RequestParam(value="imgFile2",required=false) MultipartFile excelFile2,
			@RequestParam(value="imgFile3",required=false) MultipartFile excelFile3,
			@RequestParam(value="imgFile4",required=false) MultipartFile excelFile4,
			@RequestParam(value="imgFile5",required=false) MultipartFile excelFile5,		
			@RequestParam("productTitle")String productTitle,
			@RequestParam("description")String description,
			@RequestParam("category")String category,
			@RequestParam("score")String score,
			@RequestParam("level")String level,
			@RequestParam("type")String type,
			@RequestParam("addr")String addr,
			@RequestParam("version")String version,
			@RequestParam("productId")String productId,
			@RequestParam("dimension")String dimension,
			@RequestParam("merchantId")String merchantId
			) throws LsException, IOException {
		
		Product pro  = new Product();
		MultipartFile file = excelFile;
		String logo="/loms/static/www/loms/";
		String filePath = getFile(logo);
		if(file!=null && file.getInputStream()!=null){
			try {
				FileUtils.saveFileImputStream(file.getInputStream(), webRoot+filePath);
				pro.setImgurl(url+filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		pro.setCategoryid(Long.parseLong(category));
		pro.setId(Long.parseLong(productId));
		pro.setDescription(description);
		pro.setLevel(level);
		pro.setProductName(productName);
		pro.setProductTitle(productTitle);
		pro.setScore(score);
		pro.setType(type);
		pro.setAddr(addr);
		pro.setVersion(version);
		pro.setDimension(dimension);
		pro.setMerchantId(Long.parseLong(merchantId));
		
		
		if(excelFile1!=null){
			String f1 = saveProd(excelFile1);
			pro.setImg1(f1);
		}
		if(excelFile2!=null){
			String f12 = saveProd(excelFile2);
			pro.setImg2(f12);
		}
		
		if(excelFile3!=null){
			String f3 = saveProd(excelFile3);
			pro.setImg3(f3);
		}
		
		if(excelFile4!=null){
			String f4 = saveProd(excelFile4);
			pro.setImg4(f4);
		}
		
		if(excelFile5!=null){
			String f5 = saveProd(excelFile5);
			pro.setImg5(f5);
		}
		
		proService.updateProduct(pro);
		
	}
	
	
	
	@RequestMapping(value = "/queryAddr", method = RequestMethod.POST)
    public String queryAddr(@RequestBody Map map, HttpServletRequest request ) throws LsException {
		
		String ip = (String)request.getAttribute("user_ip");
		String productid = (String)map.get("id");
		String type = (String)map.get("type");
		String userid = (String)map.get("userid");
		String pageType =(String)map.get("pageType");
		Long id = Long.parseLong(productid);
		UserLoad userload = new UserLoad();
		userload.setUserid(userid);
		userload.setProductId(id);
		userload.setPlatform(type);
		userload.setPagetype(pageType);
		userload.setIp(ip);
		try{
			userloadService.addUserLoad(userload);
		}catch (Exception e) {
			e.printStackTrace();
		}
    	String addr =proService.queryUserAddr(id, type);
    	if(addr==""){
    		return "344";
    	}
    	return addr;
    }
	
	
	@RequestMapping(value = "/queryUserProducts", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> queryUserProducts(@RequestBody Map map) throws LsException{
		//排名类型
		String type =(String)map.get("type");
		if(type==null||type==""){
		return	proService.queryUserProducts();
		}else{
		return proService.queryProductsByType(type);
		}
		
	}
	
	@RequestMapping(value = "/queryProByName", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> queryProByName(@RequestBody Map map ) throws LsException{
		
		String name = (String)map.get("name");
		
		return proService.queryUserProductsByName(name);
		
	}
	
	@RequestMapping(value = "/queryProById", method = RequestMethod.POST)
	@ResponseBody
	public List queryProById(@RequestBody Map map ) throws LsException{
		
		String id = (String)map.get("productid");
		
		return proService.queryProById(Long.parseLong(id));
		
	}
	
	@RequestMapping(value = "/deleteProducts", method = RequestMethod.POST)
	@ResponseBody
	public void deleteProducts(@RequestBody Map map ) throws LsException{
		
		String id = (String)map.get("productid");
		
		 proService.deleteProd(Long.parseLong(id));
		
	}
	
	
}