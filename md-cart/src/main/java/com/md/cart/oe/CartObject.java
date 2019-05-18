package com.md.cart.oe;

import java.util.List;

public class CartObject {

	private Long cartId;
	// 所属用户Id
	private Long memberId;
	// quantity总数量
	private Integer quantity;

	private List<GoodObject> goodObjectList;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<GoodObject> getGoodObjectList() {
		return goodObjectList;
	}

	public void setGoodObjectList(List<GoodObject> goodObjectList) {
		this.goodObjectList = goodObjectList;
	}

	@Override
	public String toString() {
		return "CartObject [cartId=" + cartId + ", memberId=" + memberId + ", quantity=" + quantity
				+ ", goodsObjectList=" + goodObjectList + "]";
	}

}
