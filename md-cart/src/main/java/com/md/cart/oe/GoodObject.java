package com.md.cart.oe;

import java.math.BigDecimal;

public class GoodObject {

	/**
	 * 购物车项id
	 */
	private Long cartItemId;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * 商品货号
	 */
	private String goodsCode;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 单价
	 */
	private BigDecimal price;

	/**
	 * 状态 1 有效 0 产品无效
	 */
	private Integer status;

	/**
	 * 规格名称
	 */
	private String skuName;

	/**
	 * 产品图
	 */
	private String imageUrl;

	/**
	 * 商品尺码
	 */
	private String goodsSize;

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	@Override
	public String toString() {
		return "GoodObject [cartItemId=" + cartItemId + ", goodsId=" + goodsId + ", goodsCode=" + goodsCode
				+ ", goodsName=" + goodsName + ", productId=" + productId + ", quantity=" + quantity + ", price="
				+ price + ", status=" + status + ", skuName=" + skuName + ", imageUrl=" + imageUrl + ", goodsSize="
				+ goodsSize + "]";
	}

}
