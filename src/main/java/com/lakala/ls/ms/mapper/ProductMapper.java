package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.dto.DatePageRequest;

@Mapper
public interface ProductMapper {

	
	public void addProduct(Product pro);
	
	public List<Product> queryProducts(DatePageRequest req);
	
	public int listProductsCount(DatePageRequest req);
	public int updateProductState(String state, long id);
	
	public void updateProduct(Product pro);
	
	public Product queryAddr(long id,String type );
	
	public  List<Product> queryUserProducts();
	
	public  List<Product> queryProductsByMerchantId(long id);
	
	public  List<Product> queryProductsByName(String name);
	
	public  List<Product> queryProductsByType(String type);
	
	public List<Product> queryHotProducts(long cateid);
	
	public Product queryProById(long id);
	
	public int deleteProd(long id);
	
}
