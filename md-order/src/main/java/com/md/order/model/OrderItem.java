package com.md.order.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.md.goods.model.PriceTag;
import com.md.goods.model.Product;

@TableName("shop_order_item")
public class OrderItem {
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	// 商品编号
	private Long goodsId;
	// 规格商品编号
	private Long productId;
	// 实实际单价（未优惠）
	private BigDecimal actualPrice;
	// 商品规格名称
	private String goodsName;
	// 优惠后的单价
	private BigDecimal unitPrice;
	// 购买数量
	private Integer quantity;
	// 所属订单编号
	private Long orderId;

	// 支付的总价（未使用优惠）
	private BigDecimal actualPay;
	// 实际需要支付的总价（已使用优惠）
	private BigDecimal originalPay;

	// by ljs 20190218 添加退货流程的三个字段（退货状态标识、退货数量、退货金额）
	// 退货状态标识
	private int refundStatus;
	// 退货数量
	private int refundQuantity;
	// 退货金额
	private BigDecimal refundAmount;

	// 价格标签
	@TableField(exist = false)
	private PriceTag priceTag;
	// 产品的规格
	@TableField(exist = false)
	private Product product;

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

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	public BigDecimal getOriginalPay() {
		return originalPay;
	}

	public void setOriginalPay(BigDecimal originalPay) {
		this.originalPay = originalPay;
	}

	public int getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}

	public int getRefundQuantity() {
		return refundQuantity;
	}

	public void setRefundQuantity(int refundQuantity) {
		this.refundQuantity = refundQuantity;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public PriceTag getPriceTag() {
		return priceTag;
	}

	public void setPriceTag(PriceTag priceTag) {
		this.priceTag = priceTag;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
