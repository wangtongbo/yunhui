package com.lakala.ls.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Category;
import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService cateService;
	
	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	@ResponseBody
	public void addProduct(
			@RequestParam("name")String name,
			@RequestParam("type")String type,
			@RequestParam("addr")String addr
			) throws LsException {
		
		
		Category cat = new Category();
		cat.setName(name);
		cat.setType(type);
		cat.setAddr(addr);
		cateService.addCategory(cat);
		
		
	}
	
	@RequestMapping(value = "/queryCategorys", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> queryCategorys() throws LsException {
		
		
		return cateService.queryCategorys();
	}
	
	@RequestMapping(value = "/queryHotCategorys", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> queryHotCategorys() throws LsException {
		
		String type ="1";
		return cateService.queryHotCategorys(type);
	}
	
	
	
	  @RequestMapping(value = "/delcategory", method = RequestMethod.POST)
	    @ResponseBody
	    public void delcategory(@RequestBody Map map) throws LsException {
	    	String contid = (String)map.get("coateid");
	    	cateService.deleteCategory(Long.parseLong(contid));
	    }

		
		@RequestMapping(value = "/queryHotProducts", method = RequestMethod.POST)
	    @ResponseBody
	    public List<Product> queryHotProducts( @RequestBody Map map) throws LsException {
		
			String cateid = (String)map.get("cateid");
			return cateService.queryHotProcutsByCateId(cateid);
		}
		
}
