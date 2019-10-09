package com.md.order.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 退货申请表 by ljs 20190219
 *
 */
@TableName("shop_aftersale_apply")
public class AfterSaleApply {
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	// 订单主表ID
	private Long orderId;
	// 订单明细ID
	private Long orderItemId;
	// 商品货号
	private String goodsCode;
	// 退货数量
	private int refundQuantity;
	// 退货金额
	private BigDecimal refundAmount;
	// 状态标识（1、已完成；3、待处理）
	private int status;
	// 备注
	private String detail;
	// 创建时间
	private Timestamp createTime;
	// 处理人
	private Long managerId;
	// 处理情况
	private int confirm;
	// 处理时间
	private Timestamp manageTime;
	// 发款人
	private String payer;
	// 发款时间
	private Timestamp payTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}



	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public Timestamp getManageTime() {
		return manageTime;
	}

	public void setManageTime(Timestamp manageTime) {
		this.manageTime = manageTime;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	@Override
	public String toString() {
		return "AfterSaleApply [id=" + id + ", orderId=" + orderId + ", orderItemId=" + orderItemId + ", goodsCode="
				+ goodsCode + ", refundQuantity=" + refundQuantity + ", refundAmount=" + refundAmount + ", status="
				+ status + ", detail=" + detail + ", createTime=" + createTime + ", managerId=" + managerId
				+ ", confirm=" + confirm + ", manageTime=" + manageTime + ", payer=" + payer + ", payTime=" + payTime
				+ "]";
	}



	
}
