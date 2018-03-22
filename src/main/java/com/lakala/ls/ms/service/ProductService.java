package com.lakala.ls.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.dto.DatePageRequest;
import com.lakala.ls.ms.mapper.ProductMapper;
import com.lakala.ls.ms.mapper.RankManagerMapper;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private RankManagerMapper rankMapper;

	public void addProduct(Product pro) throws LsException {

		productMapper.addProduct(pro);
	}

	public List<Product> listProducts(DatePageRequest req) throws LsException {

		return productMapper.queryProducts(req);
	}
	
	public int listProCount(DatePageRequest req) throws LsException {
		
		return productMapper.listProductsCount(req);
	}
	
	public int updateProductState(String state,long id)throws LsException{
		
		return productMapper.updateProductState(state, id);
		
	}
	
	public void updateProduct(Product pro)throws LsException{
		productMapper.updateProduct(pro);
		
	}
	
	
	public String queryUserAddr(long id,String type)throws LsException{
		Product pro =productMapper.queryAddr(id ,type);
		if(pro!=null){
			return pro.getAddr();
		}
		return "";
	}
	
	public List<Product> queryUserProducts () throws LsException{
		
		return productMapper.queryUserProducts();
	}
	public  List<Product> queryProductsByType(String type)throws LsException{
		
		return productMapper.queryProductsByType(type);
	}
	
	
	public List<Product> queryUserProductsByName (String name) throws LsException{
		if(name==null)
			return productMapper.queryUserProducts();
		
		return productMapper.queryProductsByName(name);
	}
		
	
	public List queryProById(Long id){
		Product pro =productMapper.queryProById(id);
		List list = new ArrayList<>();
		if(pro.getImg1()!=null&&pro.getImg1().length()>5){
			list.add(pro.getImg1());
		}
		
		if(pro.getImg2()!=null&&pro.getImg2().length()>5){
			list.add(pro.getImg2());
		}
		if(pro.getImg3()!=null&&pro.getImg3().length()>5){
			list.add(pro.getImg3());
		}
		if(pro.getImg4()!=null&&pro.getImg4().length()>5){
			list.add(pro.getImg4());
		}
		if(pro.getImg5()!=null&&pro.getImg5().length()>5){
			list.add(pro.getImg5());
		}
		
		return list;
	}
	
		
	
	public void deleteProd(long id){
		productMapper.deleteProd(id);
		rankMapper.deleteRankByProductid(id);
	}
}
