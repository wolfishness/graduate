package com.md.cart.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.md.cart.model.CartItem;

public interface ICartItemService extends IService<CartItem>{

	CartItem findByPriceTagId(Long priceTagId, Long cartId);

	List<CartItem> findByCartId(Long cartId);

}
