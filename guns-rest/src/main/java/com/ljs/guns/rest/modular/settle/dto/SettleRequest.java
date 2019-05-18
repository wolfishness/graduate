package com.ljs.guns.rest.modular.settle.dto;

import java.util.List;

import com.md.cart.model.CartItem;

public class SettleRequest {

	private Long memberId;

	private Long cartId;

	private String cartItemList;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(String cartItemList) {
		this.cartItemList = cartItemList;
	}

}
