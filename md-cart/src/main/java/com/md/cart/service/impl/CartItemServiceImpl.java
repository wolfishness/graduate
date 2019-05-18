package com.md.cart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.cart.dao.CartItemMapper;
import com.md.cart.model.CartItem;
import com.md.cart.service.ICartItemService;

@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements ICartItemService{

	@Resource
	CartItemMapper cartItemMapper;
	
	@Override
	public CartItem findByPriceTagId(Long priceTagId,Long cartId){
		CartItem item = new CartItem();
		item.setCartId(cartId);
		item.setPriceTagId(priceTagId);
		return cartItemMapper.selectOne(item);
	}
	
	@Override
	public List<CartItem> findByCartId(Long cartId){
		Wrapper<CartItem> wrapper = new EntityWrapper<>();
		wrapper.eq("cartId", cartId);
		
		return cartItemMapper.selectList(wrapper);
	}
	
	
	
	
}
