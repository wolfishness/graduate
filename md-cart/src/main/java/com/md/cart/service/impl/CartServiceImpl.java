package com.md.cart.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.util.ToolUtil;
import com.md.cart.dao.CartMapper;
import com.md.cart.model.Cart;
import com.md.cart.service.ICartService;

@Service
@Transactional
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
	@Resource
	CartMapper cartMapper;

	@Override
	public Cart addSave(Long memberId){
		Cart cart = new Cart();
		cart.setMemberId(memberId);
		Cart cartFind = cartMapper.selectOne(cart);
		
		if (ToolUtil.isNotEmpty(cartFind)) {
			System.out.println("not Empty");
			return cartFind;
		}else {
			cart.setQuantity(0);
			cartMapper.insert(cart);
			System.out.println("empty");
			return cart;
		}
	}
	
	@Override
	public Cart findByMemberId(Long memberId){
		Cart cart = new Cart();
		cart.setMemberId(memberId);
		return cartMapper.selectOne(cart);
	}
	
	
}
