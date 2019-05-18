package com.md.goods.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;

@TableName("shop_price_tag")
public class PriceTag {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	// 商品编号
	private Long goodsId;
	// 产品编号
	private Long productId;
	// 市场价格 10
	private BigDecimal marketPrice;
	// 销售价格 9
	private BigDecimal price;
	// 库存预警值
	private Integer threshold = 0;
	// 商品状态1:上架 0:下架
	private Integer status;
	// 商品的库存
	private Integer inventory;
	// 商品货号
	private String goodsCode;
	// 商品尺码
	private String goodsSize;
	// 上下架
	private BigDecimal marketTable;

	@TableField(exist = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public BigDecimal getMarketTable() {
		return marketTable;
	}

	public void setMarketTable(BigDecimal marketTable) {
		this.marketTable = marketTable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PriceTag [id=" + id + ", goodsId=" + goodsId + ", productId=" + productId + ", marketPrice="
				+ marketPrice + ", price=" + price + ", threshold=" + threshold + ", status=" + status + ", inventory="
				+ inventory + ", goodsCode=" + goodsCode + ", goodsSize=" + goodsSize + ", marketTable=" + marketTable
				+ ", name=" + name + "]";
	}

}
