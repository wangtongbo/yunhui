package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.Category;
import com.lakala.ls.ms.domain.Product;

@Mapper
public interface CategoryMapper {
	
	public void addCategory(Category cat);
	
	public List<Category> queryCategorys();

	public List<Category> queryCategorysById(long parentId);
	
	public void deleteCategory(long id);
	
	public List<Category> queryHotCategorys(String type);
	
	
}
