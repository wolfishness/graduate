package com.ljs.guns.rest.modular.settle.dto;

import com.md.member.model.Address;

public class SettleRequest {

	private Long memberId;

	private Long cartId;

	private String cartItemList;

	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SettleRequest [memberId=" + memberId + ", cartId=" + cartId + ", cartItemList=" + cartItemList
				+ ", address=" + address + "]";
	}

}
