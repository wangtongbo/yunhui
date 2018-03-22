package com.lakala.ls.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.domain.Category;
import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.mapper.CategoryMapper;
import com.lakala.ls.ms.mapper.ProductMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryMapper catMapper;
	
	@Autowired
	private ProductMapper proMapper;

	public void addCategory(Category cat) {

		catMapper.addCategory(cat);
	}

	public void deleteCategory(long id) {

		catMapper.deleteCategory(id);
	}

	public List<Category> queryCategorys() {

		return catMapper.queryCategorys();
	}
	
	public List<Category> queryHotCategorys(String type) {

		return catMapper.queryHotCategorys(type);
	}
	
	public List<Product> queryHotProcutsByCateId(String id){
		
		
		return proMapper.queryHotProducts(Long.parseLong(id));
	}
	

}
