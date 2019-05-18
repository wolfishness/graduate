package com.ljs.guns.rest.modular.cart.dto;

public class CartRequest {
	private Long memberId;

	private Long cartItemId;

	private Integer quantity;

	private Long productId;

	private String products;

	private String quantitys;

	private String itemIds;

	private Long cartId;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(String quantitys) {
		this.quantitys = quantitys;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	@Override
	public String toString() {
		return "CartRequest [memberId=" + memberId + ", cartItemId=" + cartItemId + ", quantity=" + quantity
				+ ", productId=" + productId + ", products=" + products + ", quantitys=" + quantitys + ", itemIds="
				+ itemIds + ", cartId=" + cartId + "]";
	}

}
