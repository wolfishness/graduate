package com.md.settlement.service;

import java.util.List;
import java.util.Map;

import com.md.cart.model.CartItem;
import com.md.goods.model.PriceTag;
import com.md.member.model.Address;
import com.md.order.model.Order;

public interface IAccountService {

	Map<Long, PriceTag> findPriceTag(List<CartItem> cartItems);

	Boolean inventoryEnough(List<CartItem> cartItems, Map<Long, PriceTag> priceTags);

	Order amount(List<CartItem> cartItems, Address address, Long memberId);

}
