package com.md.goods.oe;

import java.math.BigDecimal;

/**
 * 商品oe
 * 
 * @author 54476
 *
 */
public class GoodsObject {

	/**
	 * 商品id
	 */
	private Long id;

	/**
	 * 商品编号
	 */
	private String goodsCode;

	/**
	 * 商品名称
	 */
	private String goodName;

	/**
	 * 品牌名称
	 */
	private String brandName;

	/**
	 * 市场价
	 */
	private BigDecimal marketPrice;

	/**
	 * 销售价
	 */
	private BigDecimal price;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 商品图
	 */
	private String images;

	/**
	 * 商品库存
	 */
	private Long stock;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 优惠名称
	 */
	private String modelName;

	// 商品参数
	private String paramItems;
	// 商品规格
	private String specItems;

	// 商品的库存
	private Integer inventory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getParamItems() {
		return paramItems;
	}

	public void setParamItems(String paramItems) {
		this.paramItems = paramItems;
	}

	public String getSpecItems() {
		return specItems;
	}

	public void setSpecItems(String specItems) {
		this.specItems = specItems;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", goodsCode:\"" + goodsCode + "\", goodName:\"" + goodName + "\", brandName:\""
				+ brandName + "\", marketPrice:" + marketPrice + ", price:" + price + ", unit:" + unit + ", images:"
				+ images + ", stock:" + stock + ", detail:\"" + detail + "\", modelName:\"" + modelName + "\", paramItems:\""
				+ paramItems + "\", specItems:\"" + specItems + "\", inventory:" + inventory + "}";
	}

	
}
