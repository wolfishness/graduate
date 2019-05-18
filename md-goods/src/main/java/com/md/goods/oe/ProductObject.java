package com.md.goods.oe;

import java.math.BigDecimal;

public class ProductObject {

	/**
	 * 产品id
	 */
	private Long id;

	/**
	 * 市场价
	 */
	private BigDecimal marketPrice;

	/**
	 * 销售价
	 */
	private BigDecimal price;

	/**
	 * 库存
	 */
	private Integer stock;

	/**
	 * sku名称集合
	 */
	private String skuName;

	/**
	 * 产品规格图
	 */
	private String image;


	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 产品重量
	 */
	private BigDecimal weight;
	
	/**
	 * 产品尺码
	 */
	private String goodsSize;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

}
