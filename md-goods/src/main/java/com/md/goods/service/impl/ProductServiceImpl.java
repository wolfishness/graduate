package com.md.goods.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.goods.dao.ProductMapper;
import com.md.goods.model.Product;
import com.md.goods.service.IProductService;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,  Product> implements IProductService{

	
	@Resource
	ProductMapper productMapper;
	
	@Override
	public int findColorsNum(Long goodsId){
		return productMapper.findColorsNum(goodsId);
	}
}
